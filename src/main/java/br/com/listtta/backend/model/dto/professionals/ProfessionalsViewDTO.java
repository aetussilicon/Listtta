package br.com.listtta.backend.model.dto.professionals;

import java.util.Date;

import br.com.listtta.backend.model.enums.CitiesZone;
import br.com.listtta.backend.model.enums.UserGender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalsViewDTO {

    private String puid;
    private String fullName;
    private UserGender userGender;
    private Date createdDate;
    private String whatsappContact;
    private String state;
    private String city;
    private CitiesZone citiesZone;
    private String district;
    private String street;
    private String complement;
    private String zipCode;
}
