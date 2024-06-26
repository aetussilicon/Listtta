package br.com.listtta.backend.model.enums;

import lombok.Getter;

@Getter
public enum CitiesZone {
    ZONA_SUL("zona sul"),
    ZONA_NORTE("zona norte"),
    ZONA_LESTE("zona leste"),
    ZONA_OESTE("zona oeste"),
    CENTRO("centro");

    private final String cityZone;
    CitiesZone(String cityZone) {
        this.cityZone = cityZone;
    }
}
