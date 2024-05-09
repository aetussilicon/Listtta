package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findUserByPuid(String puid);
    Optional<Users> findUsersByTaxNumber(String taxNumber);
    Optional<Users> findUsersByEmail(String email);
    UserDetails findUserByEmail(String email);
    List<Users> findByRole(UserRoles userRoles);
}
