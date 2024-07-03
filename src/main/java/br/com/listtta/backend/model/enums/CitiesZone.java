package br.com.listtta.backend.model.enums;

import lombok.Getter;

@Getter
public enum CitiesZone {
    ZONA_SUL("Sul"),
    ZONA_NORTE("Norte"),
    ZONA_LESTE("Leste"),
    ZONA_OESTE("Oeste"),
    CENTRO("Centro");

    private final String cityZone;

    CitiesZone(String cityZone) {
        this.cityZone = cityZone;
    }
}
