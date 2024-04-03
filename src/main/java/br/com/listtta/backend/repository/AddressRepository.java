package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
