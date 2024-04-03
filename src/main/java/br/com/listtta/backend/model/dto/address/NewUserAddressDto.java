package br.com.listtta.backend.model.dto.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserAddressDto {

    private Long addressId;
    private String state;
    private String city;
    private String district;
    private String postalCode;
}
