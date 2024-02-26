package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.Filters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiltersRepository extends JpaRepository<Filters, Long> {
    Optional<Filters> findByFilterName(String filterName);
}
