package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.professionals.ProfessionalUpdateDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignUpDto;
import br.com.listtta.backend.model.entities.Professionals;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface ProfessionalsMapper {

    ProfessionalsMapper INSTANCE = Mappers.getMapper(ProfessionalsMapper.class);

    @Mapping(target = "userId", expression = "java(UUID.randomUUID())")
    @IterableMapping(elementTargetType = ProfessionalsSignUpDto.class)
    @Mapping(source = "instagramUrl", target = "instagramUrl")
    Professionals signUpDtoToModel(ProfessionalsSignUpDto professionalsSignUpDto);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "taxNumber", ignore = true)
    Professionals updateDtoToModel(ProfessionalUpdateDto professionalUpdateDto);

    @IterableMapping(elementTargetType = ProfessionalsDto.class)
    ProfessionalsDto professionalModelToDto (Professionals professionals);

    List<ProfessionalsDto> listModelToDto (List<Professionals> professionalsList);
}
