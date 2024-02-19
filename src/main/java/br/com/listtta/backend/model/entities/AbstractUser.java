package br.com.listtta.backend.model.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "userId")
@MappedSuperclass
public abstract class AbstractUser {

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
    
}
