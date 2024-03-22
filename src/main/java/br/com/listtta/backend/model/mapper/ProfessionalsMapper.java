package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDtoComplement;
import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfessionalsMapper {

    ProfessionalsMapper INSTANCE = Mappers.getMapper(ProfessionalsMapper.class);

    ProfessionalDetails professionalsDetailsDtoToModel(ProfessionalsSignupDtoComplement complement);
    ProfessionalsDto professionalModelToDto (Users users, ProfessionalDetails details);
}
