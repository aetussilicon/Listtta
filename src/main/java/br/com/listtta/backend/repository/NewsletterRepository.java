package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.newsletter.Newsletter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {
    Optional<Newsletter> findByEmail(String email);
}
