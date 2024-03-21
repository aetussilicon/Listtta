package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.ProfessionalsSignupDtoComplement;
import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProfessionalsMapper {

    ProfessionalsMapper INSTANCE = Mappers.getMapper(ProfessionalsMapper.class);

    ProfessionalDetails professionalsDetailsDtoToModel(ProfessionalsSignupDtoComplement complement);

}
