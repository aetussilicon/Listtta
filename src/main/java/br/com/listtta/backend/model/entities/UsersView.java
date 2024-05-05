package br.com.listtta.backend.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users_view")
@EqualsAndHashCode(of = "userId")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersView {

    @Id
    private UUID userId;

    @Column(name = "user_tag")
    private String userTag;

    @Column(name = "full_name")
    private String fullName;

    private String email;
    private String password;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "tax_number")
    private String taxNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "whatsapp_contact")
    private String whatsappContact;

    @Column(name = "user_gender")
    private String userGender;

    private String role;
    private String state;
    private String city;
}
