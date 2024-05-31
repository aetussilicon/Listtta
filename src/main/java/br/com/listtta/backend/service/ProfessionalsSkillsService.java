package br.com.listtta.backend.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import br.com.listtta.backend.model.enums.ProfessionalsType;
import org.springframework.stereotype.Service;

import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalDetails;
import br.com.listtta.backend.model.entities.filters.Filters;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSkillsDto;
import br.com.listtta.backend.model.mapper.ProfessionalsSkillsMapper;
import br.com.listtta.backend.repository.FiltersRepository;
import br.com.listtta.backend.repository.ProfessionalsSkillsRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessionalsSkillsService {

    //Repositórios
    private final ProfessionalsSkillsRepository skillsRepository;
    private final FiltersRepository filtersRepository;

    //Mappers
    private final ProfessionalsSkillsMapper skillsMapper;

    //Metódos
    private final FindUsersMethods findMethods;

    @Transactional  
    public void attachedProfessionalsSkills (UsersSignupDto signupDto) {
        ProfessionalDetails professional = findMethods.findProfessionalByPuid(signupDto.getPuid());
        if (signupDto.getProfessionalsDto().getType() == ProfessionalsType.TATTOO) {
            if (signupDto.getProfessionalsDto().getSkills() != null) {
                Set<Long> processedSkills = new HashSet<>();

                for (Long filterId : signupDto.getProfessionalsDto().getSkills()) {
                    if (processedSkills.add(filterId)) {
                        Optional<Filters> checkSkillInDb = filtersRepository.findById(filterId);
                        checkSkillInDb.ifPresent(filter -> {
                            ProfessionalsSkillsDto skillsDto = new ProfessionalsSkillsDto();
                            skillsDto.setPuid(professional.getPuid());
                            skillsDto.setFilterId(filterId);

                            skillsRepository.save(skillsMapper.skillsDtoToModel(skillsDto));
                        });
                    }
                }
            } else {
                throw new RuntimeException();
            }
        } signupDto.getProfessionalsDto().setSkills(null);
    }    
}
