package com.TicketingSystem.security;

import com.TicketingSystem.model.Principal;
import com.TicketingSystem.model.enums.UserRole;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtUtil {
    private String secret;
    private int jwtExpirationInMs;
    private int refreshExpirationDateInMs;
    private final RedisUtils redisUtils;

    public JwtUtil(RedisUtils redisUtils){
        this.redisUtils = redisUtils;
    }
    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${jwt.expirationDateInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    @Value("${jwt.refreshExpirationDateInMs}")
    public void setRefreshExpirationDateInMs(int refreshExpirationDateInMs) {
        this.refreshExpirationDateInMs = refreshExpirationDateInMs;
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();

        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

        if (roles.contains(new SimpleGrantedAuthority(UserRole.ADMIN.getAuthority()))) {
            claims.put("isAdmin", true);
        }else if (roles.contains(new SimpleGrantedAuthority(UserRole.CLIENT.getAuthority()))) {
            claims.put("isClient", true);
        }

        return doGenerateToken(claims, ((Principal)userDetails).getStringId(), false);
    }

    public String doGenerateToken(Map<String, Object> claims, String subject, boolean isRefreshToken) {
        int expirationInMs = isRefreshToken ? refreshExpirationDateInMs : jwtExpirationInMs;

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !redisUtils.hasKey(token) && !isTokenExpired(token);
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException e) {
            throw e;
        }
    }

    public long extractId(String token) {
        Claims claims = getAllClaims(token);
        return Long.parseLong(claims.getSubject());
    }

    private boolean isTokenExpired(String token){
        Date expirationDate = extractExpirationDate(token);
        return expirationDate.before(new Date());
    }

    public Date extractExpirationDate(String token){
        Claims claims = getAllClaims(token);
        return claims.getExpiration();
    }

    private Claims getAllClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();

        Boolean isAdmin = claims.get("isAdmin", Boolean.class);
        Boolean isClient = claims.get("isClient", Boolean.class);

        if (isAdmin != null && isAdmin) {
            roles.add(new SimpleGrantedAuthority(UserRole.ADMIN.getAuthority()));
        }

        if (isClient != null && isClient) {
            roles.add(new SimpleGrantedAuthority(UserRole.CLIENT.getAuthority()));
        }

        return roles;

    }

}
