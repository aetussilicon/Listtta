package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDto;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalDetails;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProfessionalsMapper {

    ProfessionalsMapper INSTANCE = Mappers.getMapper(ProfessionalsMapper.class);

    @Mapping(target = "userTag", ignore = true)
    ProfessionalDetails professionalsDetailsDtoToModel(ProfessionalsSignupDto complement);

    @Mapping(target = "detailsId", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "userTag", ignore = true)
    @Mapping(target = "puid", ignore = true)
    ProfessionalDetails updateProfessionalDtoToModel(ProfessionalsUpdateDto professionalsUpdateDto);
}
