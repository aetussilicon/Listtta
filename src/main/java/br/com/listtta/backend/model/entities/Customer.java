package br.com.listtta.backend.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "userId")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

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

    //TODO Profile Picture

}
