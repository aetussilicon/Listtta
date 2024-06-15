package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.Professionals.ProfessionalView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfessionalViewRepository extends JpaRepository<ProfessionalView, UUID> {
    Optional<ProfessionalView> findProfessionalViewByPuid(String puid);
}
