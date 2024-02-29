package br.com.listtta.backend.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name = "professionals")
public class Professionals extends AbstractUser {

    private String instagramUrl;

    //TODO SET OF SERVICES
    //TODO Profile Picture
}
