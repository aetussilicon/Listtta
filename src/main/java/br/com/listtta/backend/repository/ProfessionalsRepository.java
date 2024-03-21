package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfessionalsRepository extends JpaRepository<ProfessionalDetails, Long> {
}
