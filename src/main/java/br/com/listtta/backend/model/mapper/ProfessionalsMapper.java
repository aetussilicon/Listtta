package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDtoComplement;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDto;
import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProfessionalsMapper {

    ProfessionalsMapper INSTANCE = Mappers.getMapper(ProfessionalsMapper.class);

    ProfessionalDetails professionalsDetailsDtoToModel(ProfessionalsSignupDtoComplement complement);
    ProfessionalsDto professionalModelToDto(Users users, ProfessionalDetails details);
    ProfessionalDetails updateProfessionalDtoToModel(ProfessionalsUpdateDto professionalsUpdateDto);

    @Mapping(source = "user.role", target = "role")
    ProfessionalsDto userModelToDto(Users user, ProfessionalDetails details);

    default List<ProfessionalsDto> listModelToDto(List<Users> usersList, List<ProfessionalDetails> professionalDetailsList) {
        return usersList.stream()
                .filter(user -> user.getRole() == UserRoles.PROFESSIONAL)
                .map(user -> userModelToDto(user, findProfessionalDetailsByUser(user, professionalDetailsList)))
                .collect(Collectors.toList());
    }

    default ProfessionalDetails findProfessionalDetailsByUser(Users user, List<ProfessionalDetails> professionalDetailsList) {
        return professionalDetailsList.stream()
                .filter(details -> details.getUsers().equals(user))
                .findFirst()
                .orElse(null); // Handle if no matching ProfessionalDetails is found
    }

}
