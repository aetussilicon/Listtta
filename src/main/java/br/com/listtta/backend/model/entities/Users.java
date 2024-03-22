package br.com.listtta.backend.model.entities;

import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "userId")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(name = "full_name")
    private String fullName;
    private String username;
    private String email;
    private String password;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "phone_number")
    private String phoneNumber;
    private String state;
    private String city;
    private String district;

    @Column(name = "postal_code")
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private UserRoles role;
}
