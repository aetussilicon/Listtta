package br.com.listtta.backend.model.entities.Professionals;

import java.sql.Date;
import java.sql.Types;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import br.com.listtta.backend.model.enums.CitiesZone;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import br.com.listtta.backend.model.enums.UserGender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "professional_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "userId")
public class ProfessionalsView {

    @Id
    @Column(name = "user_id")
    private UUID userId;
    private String puid;

    @Column(name = "full_name")
    private String fullName;

    @Lob
    @Column(name = "profile_picture")
    @JdbcTypeCode(Types.VARBINARY)
    private byte[] profilePicture;

    @Column(name = "user_gender")
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Column(name = "created_date")
    private Date createdDate;
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "whatsappContact")
    private String whatsappContact;
    private String state;
    private String city;

    @Column(name = "city_zone")
    @Enumerated(EnumType.STRING)
    private CitiesZone cityZone;
    private String district;
    private String street;
    private String complement;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "professional_type")
    @Enumerated(EnumType.STRING)
    private ProfessionalsType type;

    @Column(name = "instagram_url")
    private String instagramUrl;
    private Set<Long> skills;
}
