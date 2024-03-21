package br.com.listtta.backend.model.enums;

import lombok.Getter;

@Getter
public enum ProfessionalsType {

    TATTOO("tattoo"),
    PIERCER("piercer");

    private final String professionalType;

    ProfessionalsType(String professionalType) {
        this.professionalType = professionalType;
    }

}
