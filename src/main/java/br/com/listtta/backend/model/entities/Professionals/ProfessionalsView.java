package br.com.listtta.backend.model.entities.Professionals;

import java.sql.Date;
import java.util.UUID;

import br.com.listtta.backend.model.enums.CitiesZone;
import br.com.listtta.backend.model.enums.UserGender;
import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "professional_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfessionalsView {

    @Id
    @Column(name = "user_id")
    private UUID userId;
    private String puid;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_gender")
    private UserGender userGender;

    @Column(name = "created_date")
    private Date createdDate;
    private String email;
    private String passoword;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "whatsappContact")
    private String whatsappContact;
    private UserRoles role;
    private String state;
    private String city;

    @Column(name = "city_zone")
    private CitiesZone cityZone;
    private String district;
    private String street;
    private String complement;

    @Column(name = "zip_code")
    private String zipCode;
}
