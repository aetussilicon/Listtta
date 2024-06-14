package br.com.listtta.backend.model.dto.users;

import br.com.listtta.backend.model.entities.address.Address;
import br.com.listtta.backend.model.enums.UserGender;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UsersUpdateDTO {
    private String userTag;
    private String fullName;
    private UserGender userGender;
    private String taxNumber;
    @Email private String email;
    private MultipartFile profilePicture;
    private String phoneNumber;
    private String whatsappContact;
    private Address address;
}
