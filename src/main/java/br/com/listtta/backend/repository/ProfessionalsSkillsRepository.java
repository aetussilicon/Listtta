package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.Professionals.ProfessionalsSkills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessionalsSkillsRepository extends JpaRepository<ProfessionalsSkills, UUID>{

}
