package com.zerobase.zerobase_reservation_project.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UsersRole {
    USER("USER"),
    MANAGER("MANAGER");

    private final String usersRoleText;

    UsersRole(String usersRoleText) {
        this.usersRoleText = usersRoleText;
    }

    public static UsersRole fromString(String userRoleText) {
        return UsersRole.valueOf(userRoleText.toUpperCase());
    }

    @JsonValue
    public String getText() {
        return usersRoleText;
    }
}
