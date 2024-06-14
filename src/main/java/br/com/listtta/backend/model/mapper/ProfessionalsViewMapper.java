package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessionalsViewMapper {

    ProfessionalsViewMapper INSTANCE = Mappers.getMapper(ProfessionalsViewMapper.class);

    @Mapping(source = "state", target = "address.state")
    @Mapping(source = "city", target = "address.city")
    @Mapping(source = "type", target = "professionalsDetails.type")
    @Mapping(source = "instagramUrl", target = "professionalsDetails.instagramUrl")
    @Mapping(source = "skills", target = "professionalsDetails.skills")
    ProfessionalsDTO professionalModelToDto(ProfessionalView professionalView);
    List<ProfessionalsDTO> listModelToDto(List<ProfessionalView> professionalViewList);
}
