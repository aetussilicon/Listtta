package br.com.listtta.backend.model.abstracts;

import br.com.listtta.backend.model.dto.address.AddressDTO;
import br.com.listtta.backend.model.enums.UserGender;
import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class UsersDTOAbstract {

   private String fullName;
   @Email private String email;
   private String password;
   private String puid;
   private String taxNumber;
   private String phoneNumber;
   private String whatsappContact;
   private Date createdDate;
   private UserGender gender;
   private UserRoles role;
   private AddressDTO address;
}
