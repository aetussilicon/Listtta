package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.Professionals;
import br.com.listtta.backend.model.dto.ProfessionalsDto;
import br.com.listtta.backend.model.dto.ProfessionalsSignUpDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface ProfessionalsMapper {

    ProfessionalsMapper INSTANCE = Mappers.getMapper(ProfessionalsMapper.class);

    @Mapping(target = "professionalId", expression = "java(UUID.randomUUID())")
    Professionals signUpDtoToModel(ProfessionalsSignUpDto professionalsSignUpDto);


    ProfessionalsDto professionalModeltoDto (Professionals professionals);
//    List<ProfessionalsDto> listModelToDto (List)
}
