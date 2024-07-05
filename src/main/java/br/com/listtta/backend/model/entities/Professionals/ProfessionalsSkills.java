package br.com.listtta.backend.model.entities.Professionals;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "professionals_skills")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "skillId")
public class ProfessionalsSkills {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "professionals_skills_seq_generator")
    @SequenceGenerator(name = "professionals_skills_seq_generator", sequenceName = "professionals_skills_seq", allocationSize = 1)
    @Column(name = "skill_id")
    private Long skillId;

    private String puid;
 
    @Column(name = "filter_id")
    private Long filterId;
}
