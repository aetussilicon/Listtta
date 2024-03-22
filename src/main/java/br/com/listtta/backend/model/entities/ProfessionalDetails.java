package br.com.listtta.backend.model.entities;

import br.com.listtta.backend.model.enums.ProfessionalsType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "professional_type")
    @Enumerated(EnumType.STRING)
    private ProfessionalsType type;

    @Column(name = "instagram_url")
    private String instagramUrl;
    // private Set<Filters> skills;

}
