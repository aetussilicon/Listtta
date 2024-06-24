package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.users.CannotUpdateUsersFieldsException;
import br.com.listtta.backend.exceptions.users.UserNotFound;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDetailsDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalDetails;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.Patcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfessionalsService {

    //Repositórios
    private final ProfessionalsRepository professionalsRepository;

    //Mappers
    private final ProfessionalsMapper professionalsMapper;

    //Services
    private final ProfessionalsSkillsService skillsService;

    //Validações
    private final FindUsersMethods findUsers;

    @Transactional
    public void createNewProfessionalDetails(UsersSignupDTO signupDto) {
        Users professionalUser = findUsers.findUsersByPuid(signupDto.getPuid());
    
        // Mapeando o DTO de detalhes profissionais
        ProfessionalsSignupDTO professionalDetails = signupDto.getProfessionalsDto();
    
        // Definindo o usuário e o PUID nos detalhes profissionais
        professionalDetails.setUsers(professionalUser);
        professionalDetails.setPuid(signupDto.getPuid());
    
        // Mapeando o DTO de detalhes profissionais para a entidade
        ProfessionalDetails professional = professionalsMapper.professionalsDetailsDtoToModel(professionalDetails);
    
        // Salvando os detalhes profissionais no repositório
        professionalsRepository.save(professional);
        
        skillsService.attachedProfessionalsSkills(signupDto);
    }

    //Método não salvando no banco de dados
    @Transactional
    public Users updateProfessionalDetails(String puid, UsersUpdateDTO updateDTO) {
        Users professionalUser = findUsers.findUsersByPuid(puid);
        ProfessionalDetails detailsToUpdate = findUsers.findProfessionalByUser(professionalUser);

        ProfessionalsUpdateDTO updateFieldsDTO = new ProfessionalsUpdateDTO();
        updateFieldsDTO.setType(updateDTO.getProfessionalsDetails().getType());
        updateFieldsDTO.setInstagramUrl(updateDTO.getProfessionalsDetails().getInstagramUrl());

        ProfessionalDetails updateFields = professionalsMapper.updateProfessionalDtoToModel(updateFieldsDTO);

        try {
            Patcher.patch(detailsToUpdate, updateFields);
            professionalsRepository.save(detailsToUpdate);

            if (updateDTO.getProfessionalsDetails().getSkills() != null) {
                skillsService.updateProfessionalSkills(puid, updateDTO.getProfessionalsDetails().getSkills());
            }

        } catch (IllegalAccessException e) {
            throw new CannotUpdateUsersFieldsException("Não foi possível atualizar o usuário: " + e);
        }

        return professionalUser;
    }

    protected ProfessionalsDetailsDTO getProfessional(String puid, Users professional) {
       ProfessionalDetails details = professionalsRepository.findProfessionalByUsers(professional).orElseThrow(UserNotFound::new);
       ProfessionalsDetailsDTO returnDTO = professionalsMapper.getProfessionalDetails(professional, details);

       returnDTO.setSkills(skillsService.getSkills(puid));

        return returnDTO;
    }
}
