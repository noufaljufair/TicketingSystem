package com.TicketingSystem.model.enums;

public enum UserPermission {
    TICKET_READ("ticket:read");

    private final String permission;

    UserPermission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
