package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsSkillsDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalDetails;
import br.com.listtta.backend.model.entities.filters.Filters;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import br.com.listtta.backend.model.mapper.ProfessionalsSkillsMapper;
import br.com.listtta.backend.repository.FiltersRepository;
import br.com.listtta.backend.repository.ProfessionalsSkillsRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
    public void attachedProfessionalsSkills (UsersSignupDTO signupDto) {
        ProfessionalDetails professional = findMethods.findProfessionalByPuid(signupDto.getPuid());
        if (signupDto.getProfessionalsDto().getType() == ProfessionalsType.TATTOO) {
            if (signupDto.getProfessionalsDto().getSkills() != null) {
                Set<Long> processedSkills = new HashSet<>();

                for (Long filterId : signupDto.getProfessionalsDto().getSkills()) {
                    if (processedSkills.add(filterId)) {
                        Optional<Filters> checkSkillInDb = filtersRepository.findById(filterId);
                        checkSkillInDb.ifPresent(filter -> {
                            ProfessionalsSkillsDTO skillsDto = new ProfessionalsSkillsDTO();
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
