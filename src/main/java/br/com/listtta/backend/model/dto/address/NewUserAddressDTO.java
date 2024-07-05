package br.com.listtta.backend.model.dto.address;

import br.com.listtta.backend.model.entities.users.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserAddressDTO {

    private Long addressId;
    private Users users;
    private String puid;
    private String state;
    private String city;
}
