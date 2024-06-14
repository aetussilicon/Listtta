package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.filters.CreateNewFilterDTO;
import br.com.listtta.backend.model.dto.filters.FiltersDTO;
import br.com.listtta.backend.model.dto.filters.UpdateFilterDTO;
import br.com.listtta.backend.model.entities.filters.Filters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FiltersMapper {

    FiltersMapper INSTANCE = Mappers.getMapper(FiltersMapper.class);

    @Mapping(target = "filterId", source = "filterId")
    Filters createNewFilterDtoToModel (CreateNewFilterDTO createNewFilterDto);

    @Mapping(target = "filterId", ignore = true)
    Filters updateFilterDtoToModel (UpdateFilterDTO updateFilterDto);
    FiltersDTO filtersModelToDto (Filters filters);
    List<FiltersDTO> listFiltersModelToDto (List<Filters> filtersList);

}
