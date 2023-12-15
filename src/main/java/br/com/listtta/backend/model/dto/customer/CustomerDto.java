package br.com.listtta.backend.model.dto.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private String state;
    private String city;
    private String district;
    private String postalCode;

}
