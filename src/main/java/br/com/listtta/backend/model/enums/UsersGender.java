package br.com.listtta.backend.model.enums;

import lombok.Getter;

@Getter
public enum UsersGender {

    MASCULINO("masculino"),
    FEMININO("feminino"),
    OUTROS("outros");

    private final String userGender;

    UsersGender(String userGender) {
        this.userGender = userGender;
    }
}
