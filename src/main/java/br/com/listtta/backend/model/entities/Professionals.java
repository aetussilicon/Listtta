package br.com.listtta.backend.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table (name = "professionals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "professionalId")
public class Professionals {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID professionalId;

    @Column(name = "full_name")
    private String fullName;
    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "tax_number")
    private String taxNumber;
    private String email;
    private String password;
    private String state;
    private String city;
    private String district;

    @Column(name = "postal_code")
    private String postalCode;
    private String instagramUrl;

    //TODO SET OF SERVICES
    //TODO Profile Picture
}
