package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.dto.address.AddressDTO;
import br.com.listtta.backend.model.enums.UserGender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProfessionalsViewDTO {

    private String puid;
    private String fullName;
    private UserGender gender;
    private Date createdDate;
    private String whatsappContact;
    private AddressDTO address;
    private DetailsDTO details;
    private String profilePicture;
    private String mimetype;
}
