package br.com.listtta.backend.model.enums;

import lombok.Getter;

@Getter
public enum Gender {

    MASCULINO("masculino"),
    FEMININO("feminino"),
    OUTROS("outros");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
