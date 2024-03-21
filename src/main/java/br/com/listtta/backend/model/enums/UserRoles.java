package br.com.listtta.backend.model.enums;

import lombok.Getter;

@Getter
public enum UserRoles {

    USER("user"),
    PROFESSIONAL("professional"),
    ADMIN("admin");

    private final String userRole;

    UserRoles(String userRole) {
        this.userRole = userRole;
    }

}
