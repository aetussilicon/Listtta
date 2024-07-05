package br.com.listtta.backend.model.enums;

import lombok.Getter;

@Getter
public enum UserGender {

    MASCULINO("masculino"),
    FEMININO("feminino"),
    OUTROS("outros");

    private final String gender;

    UserGender(String gender) {
        this.gender = gender;
    }
}
