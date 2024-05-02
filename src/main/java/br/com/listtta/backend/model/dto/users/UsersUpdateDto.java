package br.com.listtta.backend.model.dto.users;

import br.com.listtta.backend.model.entities.Address;
import br.com.listtta.backend.model.enums.UserGender;
import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersUpdateDto {

    private String userTag;
    private String fullName;
    private UserGender userGender;
    private String taxNumber;
    @Email private String email;
    private String phoneNumber;
    private String whatsappContact;
    private Address address;
}
