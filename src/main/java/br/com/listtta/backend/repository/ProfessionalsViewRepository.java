package br.com.listtta.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.listtta.backend.model.entities.Professionals.ProfessionalsView;

public interface ProfessionalsViewRepository extends JpaRepository<ProfessionalsView, UUID> {

}
