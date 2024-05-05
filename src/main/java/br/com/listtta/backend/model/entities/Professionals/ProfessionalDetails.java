package br.com.listtta.backend.model.entities.Professionals;

import br.com.listtta.backend.model.entities.filters.Filters;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Table(name = "professionals")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professionals_seq_generator")
    @SequenceGenerator(name = "professionals_seq_generator", sequenceName = "professionals_seq", allocationSize = 1)
    @Column(name = "details_id")
    private Long detailsId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users users;
    private String userTag;
    private String puid;

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
    private Set<Filters> filterId;
}