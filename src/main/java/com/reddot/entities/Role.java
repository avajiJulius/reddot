package com.reddot.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.reddot.entities.Permission.*;

public enum Role {
    USER(Set.of(USER_READ)),
    MANAGER(Set.of(USER_WRITE, USER_READ)),
    ADMIN(Set.of(USER_WRITE, USER_READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}