package br.com.listtta.backend.model.dto.authentication;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

public record LoginDto(String email, String password) {
}
