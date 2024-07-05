package br.com.listtta.backend.model.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsViewDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalsView;

@Mapper(componentModel = "spring")
public interface ProfessionalsViewMapper {

    ProfessionalsViewMapper INSTANCE = Mappers.getMapper(ProfessionalsViewMapper.class);

    @Mappings({
            @Mapping(source = "state", target = "address.state"),
            @Mapping(source = "city", target = "address.city"),
            @Mapping(source = "cityZone", target = "address.cityZone"),
            @Mapping(source = "district", target = "address.district"),
            @Mapping(source = "street", target = "address.street"),
            @Mapping(source = "complement", target = "address.complement"),
            @Mapping(source = "zipCode", target = "address.zipCode"),
            @Mapping(source = "type", target = "details.type"),
            @Mapping(source = "instagramUrl", target = "details.instagramUrl"),
            @Mapping(source = "skills", target = "details.skills")

    })
    ProfessionalsViewDTO getProfessionalView(ProfessionalsView professionalsView);

    @InheritConfiguration
    List<ProfessionalsViewDTO> listAllProfessionalsView(List<ProfessionalsView> professionalsViews);
}
