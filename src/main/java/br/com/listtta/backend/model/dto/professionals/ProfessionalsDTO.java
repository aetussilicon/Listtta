package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.dto.address.AddressDTO;
import br.com.listtta.backend.model.enums.UserGender;
import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalsDTO {

    private String puid;
    private String fullName;
    @Email
    private String email;
    private String password;
    private String userTag;
    private UserRoles role;
    private UserGender userGender;
    private String taxNumber;
    private String phoneNumber;
    private AddressDTO address;
    private ProfessionalsDetailsDTO professionalsDetails;
}
