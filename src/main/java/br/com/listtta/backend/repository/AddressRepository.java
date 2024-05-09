package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findUserAddressByPuid(String puid);
}
