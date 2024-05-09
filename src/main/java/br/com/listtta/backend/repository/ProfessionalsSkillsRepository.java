package br.com.listtta.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.listtta.backend.model.entities.Professionals.ProfessionalsSkills;

public interface ProfessionalsSkillsRepository extends JpaRepository<ProfessionalsSkills, UUID>{

}
