package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.filters.CreateNewFilterDto;
import br.com.listtta.backend.model.dto.filters.FiltersDto;
import br.com.listtta.backend.model.dto.filters.UpdateFilterDto;
import br.com.listtta.backend.model.entities.filters.Filters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FiltersMapper {

    FiltersMapper INSTANCE = Mappers.getMapper(FiltersMapper.class);

    @Mapping(target = "filterId", source = "filterId")
    Filters createNewFilterDtoToModel (CreateNewFilterDto createNewFilterDto);
    Filters updateFilterDtoToModel (UpdateFilterDto updateFilterDto);
    FiltersDto filtersModelToDto (Filters filters);
    List<FiltersDto> listFiltersModelToDto (List<Filters> filtersList);

}
