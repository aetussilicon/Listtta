package br.com.listtta.backend.model.entities.Professionals;

import br.com.listtta.backend.model.entities.filters.Filters;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import br.com.listtta.backend.model.enums.UsersGender;
import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "professional_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "userId")
public class ProfessionalView {

    @Id
    private UUID userId;

    @Column(name = "user_tag")
    private String userTag;

    @Column(name = "full_name")
    private String fullName;

    private String email;
    private String password;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "whatsapp_contact")
    private String whatsappContact;

    @Column(name = "user_gender")
    @Enumerated(EnumType.STRING)
    private UsersGender usersGender;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    private String state;
    private String city;

    @Column(name = "professional_type")
    @Enumerated(EnumType.STRING)
    private ProfessionalsType type;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @ManyToMany
    @JoinTable(
            name = "professionals_skills",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "filter_id")
    )
    private Set<Filters> skills;

}
