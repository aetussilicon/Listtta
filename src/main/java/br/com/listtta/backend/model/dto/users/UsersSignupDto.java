package br.com.listtta.backend.model.dto.users;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDto;
import br.com.listtta.backend.model.enums.Gender;
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
    @NotNull private String fullName;
    private Date createdDate;
    @NotNull @Email private String email;
    @NotNull private String password;
    @NotNull private String taxNumber;
    @NotNull private String phoneNumber;
    @NotNull private String whatsappContact;
    @NotNull private Gender userGender;
    @NotNull private UserRoles role;
    private String state;
    private String city;
    private String district;
    private String postalCode;
    private ProfessionalsSignupDto professionalsDto;
}
