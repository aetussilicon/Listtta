package br.com.listtta.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.listtta.backend.model.entities.newsletter.Newsletter;

public interface NewsletterRepository extends JpaRepository<Newsletter, Long> {

}
