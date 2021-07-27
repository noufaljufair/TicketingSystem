package com.TicketingSystem.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

public class Principal implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 550L;
    private static final Log logger = LogFactory.getLog(User.class);
    private String password;
    private final long id;
    private String email;
    private final Set<GrantedAuthority> authorities;

    public Principal(long id, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    public Principal(long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet(new Principal.AuthorityComparator());
        Iterator var2 = authorities.iterator();

        while(var2.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority)var2.next();
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    @Override
    public void eraseCredentials() {
        password = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public long getId(){
        return id;
    }

    public String getStringId(){
        return Long.toString(id);
    }
    public boolean equals(Object obj) {
        return obj instanceof Principal ? email.equals(((Principal)obj).email) : false;
    }

    public int hashCode() {
        return email.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getName()).append(" [");
        sb.append("Email=").append(email).append(", ");
        sb.append("Password=[PROTECTED], ");
        sb.append("Granted Authorities=").append(authorities).append("]");
        return sb.toString();
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = 550L;

        private AuthorityComparator() {
        }

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            } else {
                return g1.getAuthority() == null ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
            }
        }
    }
}
