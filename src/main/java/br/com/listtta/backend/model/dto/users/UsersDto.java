package br.com.listtta.backend.model.dto.users;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {

    private String fullName;
    @Email private String email;
    private String password;
    private String taxNumber;
    private String phoneNumber;
    private String state;
    private String city;
    private String district;
    private String postalCode;

}
