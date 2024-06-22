package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsDetailsDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalDetails;
import br.com.listtta.backend.model.entities.users.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfessionalsMapper {

    ProfessionalsMapper INSTANCE = Mappers.getMapper(ProfessionalsMapper.class);

    @Mapping(target = "userTag", ignore = true)
    ProfessionalDetails professionalsDetailsDtoToModel(ProfessionalsSignupDTO complement);

    @Mapping(target = "detailsId", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "userTag", ignore = true)
    @Mapping(target = "puid", ignore = true)
    ProfessionalDetails updateProfessionalDtoToModel(ProfessionalsUpdateDTO professionalsUpdateDto);

    @Mapping(source = "details.puid", target = "puid")
    ProfessionalsDetailsDTO getProfessionalDetails(Users professional, ProfessionalDetails details);
}
