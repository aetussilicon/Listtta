package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.Professionals.ProfessionalView;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import br.com.listtta.backend.model.enums.UserGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfessionalViewRepository extends JpaRepository<ProfessionalView, UUID> {
    Optional<ProfessionalView> findProfessionalViewByPuid(String puid);
}
