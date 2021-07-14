package com.TicketingSystem.security;


import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public JwtRequestFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
      try {
          String jwtToken = extractJwt(httpServletRequest);

          if(StringUtils.hasText(jwtToken)){
              String email = jwtUtil.extractEmail(jwtToken);
              UserDetails userDetails = userDetailsService.loadUserByUsername(email);

              if (jwtUtil.validateToken(jwtToken, userDetails)){
                  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                          UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                  SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
              }
          }
      }catch(ExpiredJwtException e){
          String isRefreshToken = httpServletRequest.getHeader("isRefreshToken");
          String requestURL = httpServletRequest.getRequestURL().toString();

          if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshToken")) {
              allowForRefreshToken(e, httpServletRequest);
          } else {
              httpServletRequest.setAttribute("exception", e);
          }
      }catch(BadCredentialsException e){
          httpServletRequest.setAttribute("exception", e);
      }

      filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private String extractJwt(HttpServletRequest request) {
        final String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

        // create a UsernamePasswordAuthenticationToken with null values.
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                null, null, null);
        // After setting the Authentication in the context, we specify
        // that the current user is authenticated. So it passes the
        // Spring Security Configurations successfully.
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        // Set the claims so that in controller we will be using it to create
        // new JWT
        request.setAttribute("claims", ex.getClaims());
    }

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
