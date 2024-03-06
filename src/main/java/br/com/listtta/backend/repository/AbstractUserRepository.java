package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.AbstractUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface AbstractUserRepository<T> extends JpaRepository<T, UUID> {
    Optional<T> findUserByUsername(String username);
}
