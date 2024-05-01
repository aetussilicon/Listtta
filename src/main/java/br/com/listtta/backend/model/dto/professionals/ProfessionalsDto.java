package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.enums.ProfessionalsType;
import br.com.listtta.backend.model.enums.UserGender;
import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalsDto {

    private String fullName;
    @Email
    private String email;
    private String password;
    private String userTag;
    private UserRoles role;
    private UserGender userGender;
    private String taxNumber;
    private String phoneNumber;
    private String state;
    private String city;
    private String district;
    private String postalCode;
    private ProfessionalsType type;
    private String instagramUrl;
}
