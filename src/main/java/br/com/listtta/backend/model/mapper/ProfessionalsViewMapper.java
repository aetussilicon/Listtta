package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessionalsViewMapper {

    ProfessionalsViewMapper INSTANCE = Mappers.getMapper(ProfessionalsViewMapper.class);

    ProfessionalsDto professionalModelToDto(ProfessionalView professionalView);
    List<ProfessionalsDto> listModelToDto(List<ProfessionalView> professionalViewList);
}
