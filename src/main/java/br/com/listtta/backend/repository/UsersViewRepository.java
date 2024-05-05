package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.users.UsersView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersViewRepository extends JpaRepository<UsersView, UUID> {
    Optional<UsersView> findUsersViewByPuid(String puid);
}
