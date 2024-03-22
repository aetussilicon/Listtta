package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findUserByUsername(String username);
    Optional<Users> findUserByTaxNumber(String taxNumber);

    @Query("SELECT u FROM Users u LEFT JOIN ProfessionalDetails pd ON u.userId = pd.users.userId WHERE u.userId = :userId")
    Users findProfessionalById(@Param("userId") UUID userId);
}
