package br.com.listtta.backend.model.dto.customer;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerUpdateDto {

    private String fullName;
    private String phoneNumber;
    @Email private String email;
    private String password;
    private String state;
    private String city;
    private String district;
    private String postalCode;

}
