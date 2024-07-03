package br.com.listtta.backend.model.dto.address;

import br.com.listtta.backend.model.enums.CitiesZone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private String state;
    private String city;
    private String cityZone;
    private String district;
    private String street;
    private String complement;
    private String zipCode;
}
