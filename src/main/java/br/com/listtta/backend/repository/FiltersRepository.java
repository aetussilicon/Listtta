package br.com.listtta.backend.repository;

import br.com.listtta.backend.model.entities.filters.Filters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiltersRepository extends JpaRepository<Filters, Long> {
    Optional<Filters> findByFilterName(String filterName);
}
