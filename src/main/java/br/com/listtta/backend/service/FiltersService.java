package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.UpdateFieldsException;
import br.com.listtta.backend.model.dto.generics.CreateNewFilterDto;
import br.com.listtta.backend.model.dto.generics.FiltersDto;
import br.com.listtta.backend.model.dto.generics.UpdateFilterDto;
import br.com.listtta.backend.model.entities.Filters;
import br.com.listtta.backend.model.mapper.FiltersMapper;
import br.com.listtta.backend.repository.FiltersRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FiltersService {

    private final FiltersRepository filtersRepository;
    private final FiltersMapper filtersMapper;

    private Filters checkFilterInDatabaseByName(String filterName) {
        Optional<Filters> checkFilterInDatabaseByName = filtersRepository.findByFilterName(filterName);

        if (checkFilterInDatabaseByName.isPresent()) {
            return checkFilterInDatabaseByName.get();
        }
        throw new RuntimeException("Filtro não encontrado!");
    }

    private Filters checkFilterInDatabaseById(Long filterId) {
        Optional<Filters> checkFilterInDatabaseById = filtersRepository.findById(filterId);

        if (checkFilterInDatabaseById.isPresent()) {
            return checkFilterInDatabaseById.get();
        }
        throw new RuntimeException("Filtro não encontrado!");
    }

    public Filters createNewFilter(CreateNewFilterDto createNewFilterDto) {
        return filtersRepository.save(filtersMapper.createNewFilterDtoToModel(createNewFilterDto));
    }

    public Filters patchFilter(Long filterId, UpdateFilterDto updateFilterDto) {
        Filters checkedFilter = checkFilterInDatabaseById(filterId);
        Filters fieldsToUpdate = filtersMapper.updateFilterDtoToModel(updateFilterDto);

        for (Field field : Filters.class.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.get(fieldsToUpdate) != null && !field.get(fieldsToUpdate).equals(field.get(checkedFilter))) {
                    field.set(checkedFilter, field.get(fieldsToUpdate));
                }
            } catch (IllegalAccessException e) {
                throw new UpdateFieldsException("Não foi possível atualizar o filtro");
            }
        }
        return checkedFilter;
    }

    public FiltersDto getOneFilter(String filterName) {
        return filtersMapper.filtersModelToDto(checkFilterInDatabaseByName(filterName));
    }

    public List<FiltersDto> gerAllFilters() {
        return filtersMapper.listFiltersModelToDto(filtersRepository.findAll());
    }

    public String deleteFilter(Long filterId) {
        checkFilterInDatabaseById(filterId);
        filtersRepository.deleteById(filterId);
        return "Filtro deleteado com sucesso!";
    }
}
