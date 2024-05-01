package br.com.listtta.backend.model.dto.users;

import br.com.listtta.backend.model.dto.address.NewUserAddressDto;
import br.com.listtta.backend.model.entities.Address;
import br.com.listtta.backend.service.AddressService;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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
