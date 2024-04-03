package br.com.listtta.backend.model.dto.users;

import br.com.listtta.backend.model.entities.Address;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsersSignupDto {

    private UUID userId;
    @NotNull private String fullName;
    private String userTag;
    @NotNull @Email private String email;
    @NotNull private String password;
    @NotNull private String taxNumber;
    @NotNull private String phoneNumber;
    @NotNull Address userAddress;
    //    @NotNull private String state;
//    @NotNull private String city;
//    @NotNull private String district;
//    @NotNull private String postalCode;
    @NotNull private UserRoles role;

}
