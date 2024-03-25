package br.com.listtta.backend.model.enums;

public enum UserRoles {

    USER("user"),
    PROFESSIONAL("professional"),
    ADMIN("admin");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
