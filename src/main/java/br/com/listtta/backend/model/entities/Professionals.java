package br.com.listtta.backend.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name = "professionals")
@Getter
@Setter
public class Professionals extends AbstractUser {

    @Column(name = "instagram_url")
    private String instagramUrl;

    //TODO SET OF SERVICES
    //TODO Profile Picture
}
