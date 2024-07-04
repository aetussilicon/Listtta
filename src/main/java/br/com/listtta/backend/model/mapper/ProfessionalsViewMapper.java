package br.com.listtta.backend.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsViewDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalsView;

@Mapper(componentModel = "spring")
public interface ProfessionalsViewMapper {

    ProfessionalsViewMapper INSTANCE = Mappers.getMapper(ProfessionalsViewMapper.class);

    ProfessionalsViewDTO getProfessionalView(ProfessionalsView professionalsView);

    List<ProfessionalsViewDTO> listAllProfessionalsView(List<ProfessionalsView> professionalsViews);
}
