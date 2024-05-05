package br.com.listtta.backend.model.dto.users;

import br.com.listtta.backend.model.entities.address.Address;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {

    private String fullName;
    @Email private String email;
    private String userTag;
    private String puid;
    private String password;
    private String taxNumber;
    private String phoneNumber;
    private Address address;
}
