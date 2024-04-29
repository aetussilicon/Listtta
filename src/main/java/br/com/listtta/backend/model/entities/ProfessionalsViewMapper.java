package br.com.listtta.backend.model.entities;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessionalsViewMapper {

    ProfessionalsViewMapper INSTANCE = Mappers.getMapper(ProfessionalsViewMapper.class);

    List<ProfessionalsDto> listModelToDto(List<ProfessionalView> professionalViewList);

}
