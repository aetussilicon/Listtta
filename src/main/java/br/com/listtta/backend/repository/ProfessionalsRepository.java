package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.Professionals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfessionalsRepository extends AbstractUserRepository<Professionals> {
}
