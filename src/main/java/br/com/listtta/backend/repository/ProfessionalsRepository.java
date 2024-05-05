package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.entities.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessionalsRepository extends JpaRepository<ProfessionalDetails, Long> {

    Optional<ProfessionalDetails> findProfessionalByUsers(Users users);

}
