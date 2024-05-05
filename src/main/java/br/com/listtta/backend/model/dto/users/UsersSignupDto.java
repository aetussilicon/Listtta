package br.com.listtta.backend.model.dto.users;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDto;
import br.com.listtta.backend.model.entities.Address;
import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class UsersSignupDto {

    private UUID userId;
    private String puid;
    private Date createdDate;
    @NotNull @Email private String email;
    @NotNull private String password;
    @NotNull private UserRoles role;
    @NotNull private Address address;
    private ProfessionalsSignupDto professionalsDto;
}
