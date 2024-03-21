package br.com.listtta.backend.model.dto.users;

import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersUpdateDto {

    private String fullName;
    private String phoneNumber;
    @Email private String email;
    private String password;
    private UserRoles role;
    private String state;
    private String city;
    private String district;
    private String postalCode;

}
