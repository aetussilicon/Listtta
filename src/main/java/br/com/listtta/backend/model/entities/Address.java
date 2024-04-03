package br.com.listtta.backend.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "addressId")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_generator")
    @SequenceGenerator(name = "address_seq_generator", sequenceName = "address_seq", allocationSize = 1)
    private Long addressId;
    private String state;
    private String city;
    private String district;

    @Column(name = "postal_code")
    private String postalCode;

}
