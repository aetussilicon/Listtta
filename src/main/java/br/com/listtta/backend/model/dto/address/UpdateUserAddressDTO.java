package br.com.listtta.backend.model.dto.address;

import br.com.listtta.backend.model.enums.CitiesZone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserAddressDTO {

    private String state;
    private String city;
    private CitiesZone cityZone;
    private String district;
    private String street;
    private String complement;
    private String zipCode;
}
