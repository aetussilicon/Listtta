package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.generics.CreateNewFilterDto;
import br.com.listtta.backend.model.entities.Filters;
import br.com.listtta.backend.model.mapper.FiltersMapper;
import br.com.listtta.backend.repository.FiltersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FiltersService {

    private final FiltersRepository filtersRepository;
    private final FiltersMapper filtersMapper;

    public Filters createNewFilter(CreateNewFilterDto createNewFilterDto) {
        return filtersRepository.save(filtersMapper.createNewFilterDtoToModel(createNewFilterDto));
    }

}
