package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findUserByUsername(String username);
    Optional<Users> findUserByTaxNumber(String taxNumber);
}
