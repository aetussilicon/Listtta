package br.com.listtta.backend.model.entities.Professionals;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
