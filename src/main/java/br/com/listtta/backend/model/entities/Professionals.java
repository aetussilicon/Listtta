package br.com.listtta.backend.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table (name = "professionals")
@Getter
@Setter
public class Professionals extends AbstractUser {

    @Column(name = "instagram_url")
    private String instagramUrl;

    @ManyToMany
    @JoinTable(
            name = "professional_skills",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "filter_id")
    )
    private Set<Filters> professionalSkills;
    //TODO SET OF SERVICES
    //TODO Profile Picture
}
