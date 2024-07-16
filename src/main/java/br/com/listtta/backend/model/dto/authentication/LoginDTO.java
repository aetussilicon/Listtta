package br.com.listtta.backend.model.dto.authentication;

public record LoginDTO(String email, String password, boolean rememberMe) {
}
