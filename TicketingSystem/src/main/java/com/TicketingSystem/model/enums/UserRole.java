package com.TicketingSystem.model.enums;

import com.TicketingSystem.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    ADMIN(new HashSet<UserPermission>())
    , CLIENT(new HashSet<UserPermission>());

     UserRole(Set<UserPermission> permissions){
        this.permissions = permissions;
    }

    private final Set<UserPermission> permissions;

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("Role_" + name()));

        return simpleGrantedAuthorities;
    }
}
