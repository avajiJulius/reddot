package com.reddot.entities;

public enum Permission {
    USER_USER("user:user"),
    USER_ADMIN("user:admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
