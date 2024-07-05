package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsSkillsDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
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

    public void updateProfessionalSkills(String puid, Set<Long> newSkills) {
        ProfessionalDetails professional = findMethods.findProfessionalByPuid(puid);

        if (professional.getType() == ProfessionalsType.TATTOO){
            Set<Long> existingSkills = skillsRepository.findSkillsByPuid(puid);

            int i;
            for (i = 0; i <= 5 ; i++) {
                existingSkills.forEach(skillId -> skillsRepository.deleteByPuidAndFilterId(puid, skillId));
            }
//            existingSkills.stream()
//                    .filter(skillId -> !newSkills.contains(skillId))
//                    .forEach(skillId -> {
//                        skillsRepository.deleteByPuidAndFilterId(puid, skillId);
//                    });

            for (Long filterId : newSkills) {
                if (!existingSkills.contains(filterId)) {
                    Optional<Filters> checkSkillInDB = filtersRepository.findById(filterId);
                    checkSkillInDB.ifPresent(filter -> {
                        ProfessionalsSkillsDTO skillsDTO = new ProfessionalsSkillsDTO();
                        skillsDTO.setPuid(professional.getPuid());
                        skillsDTO.setFilterId(filterId);
                        skillsRepository.save(skillsMapper.skillsDtoToModel(skillsDTO));
                    });
                }
            }
        } else {
            throw new RuntimeException("Apenas tatuadores possuem especialidades");
        }
    }
    public Set<Long> getSkills(String puid) {
        Set<Long> skills = skillsRepository.findSkillsByPuid(puid);
        return skills;
    }
}
