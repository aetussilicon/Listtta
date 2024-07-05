package br.com.listtta.backend.model.dto.users;

import br.com.listtta.backend.model.enums.UserGender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UsersViewDTO {

    String userTag;
    String puid;
    String fullName;
    UserGender gender;
    Date createdDate;
    String email;
    String taxNumber;
    String phoneNumber;
    String whatsappContact;
    String state;
    String city;
}
