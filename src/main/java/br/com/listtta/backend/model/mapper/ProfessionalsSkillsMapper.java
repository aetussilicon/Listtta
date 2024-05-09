package br.com.listtta.backend.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.listtta.backend.model.entities.Professionals.ProfessionalsSkills;

@Mapper(componentModel = "spring")
public interface ProfessionalsSkillsMapper {

    ProfessionalsSkillsMapper INSTANCE = Mappers.getMapper(ProfessionalsSkillsMapper.class);

    ProfessionalsSkills skillsDtoToModel (ProfessionalsSkillsDto skillsDto);
}
