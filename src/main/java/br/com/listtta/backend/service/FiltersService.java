package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.generics.CreateNewFilterDto;
import br.com.listtta.backend.model.dto.generics.FiltersDto;
import br.com.listtta.backend.model.entities.Filters;
import br.com.listtta.backend.model.mapper.FiltersMapper;
import br.com.listtta.backend.repository.FiltersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FiltersService {

    private final FiltersRepository filtersRepository;
    private final FiltersMapper filtersMapper;

    public Filters createNewFilter(CreateNewFilterDto createNewFilterDto) {
        return filtersRepository.save(filtersMapper.createNewFilterDtoToModel(createNewFilterDto));
    }

    public FiltersDto getOneFilter(String filterName) {
        Optional<Filters> checkFilterInDatabase = filtersRepository.findByFilterName(filterName);
        if (checkFilterInDatabase.isPresent()) {
            return filtersMapper.filtersModelToDto(checkFilterInDatabase.get());
        } else {
            throw new RuntimeException("Filtro Não Encontrado!");
        }
    }

    public List<FiltersDto> gerAllFilters() {
        return filtersMapper.listFiltersModelToDto(filtersRepository.findAll());
    }

}
