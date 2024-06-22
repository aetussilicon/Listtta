package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.users.CannotUpdateUsersFieldsException;
import br.com.listtta.backend.exceptions.users.UserNotFound;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDetailsDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalDetails;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.model.mapper.ProfessionalsViewMapper;
import br.com.listtta.backend.repository.ProfessionalViewRepository;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.Patcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionalsService {

    //Repositórios
    private final ProfessionalsRepository professionalsRepository;
    private final UsersRepository usersRepository;
    private final ProfessionalViewRepository professionalViewRepository;

    //Mappers
    private final ProfessionalsMapper professionalsMapper;
    private final ProfessionalsViewMapper professionalsViewMapper;

    //Services
    private final ProfessionalsSkillsService skillsService;

    //Validações
    private final FindUsersMethods findUsers;
    private final Tika tika = new Tika();

    @Transactional
    public ProfessionalDetails createNewProfessionalDetals(UsersSignupDTO signupDto) {
        Users professionalUser = findUsers.findUsersByPuid(signupDto.getPuid());
    
        // Mapeando o DTO de detalhes profissionais
        ProfessionalsSignupDTO professionalDetails = signupDto.getProfessionalsDto();
    
        // Definindo o usuário e o PUID nos detalhes profissionais
        professionalDetails.setUsers(professionalUser);
        professionalDetails.setPuid(signupDto.getPuid());
    
        // Mapeando o DTO de detalhes profissionais para a entidade
        ProfessionalDetails professional = professionalsMapper.professionalsDetailsDtoToModel(professionalDetails);
    
        // Salvando os detalhes profissionais no repositório
        ProfessionalDetails savedProfessional = professionalsRepository.save(professional);
        
        skillsService.attachedProfessionalsSkills(signupDto);

        return savedProfessional;
    }
    
    //Método não salvando no banco de dados
    public ProfessionalDetails updateProfessionalDetails(String userTag, ProfessionalsUpdateDTO professionalsUpdateDto) {
        Users professionalUser = findUsers.findUsersByPuid(userTag);
        ProfessionalDetails detailsToUpdate = findUsers.findProfessionalByUser(professionalUser);
        ProfessionalDetails updateFields = professionalsMapper.updateProfessionalDtoToModel(professionalsUpdateDto);

        try {
            Patcher.patch(detailsToUpdate, updateFields);
            professionalsRepository.save(detailsToUpdate);
        } catch (IllegalAccessException e) {
            throw new CannotUpdateUsersFieldsException("Não foi possível atualizar o usuário: " + e);
        }
        return detailsToUpdate;
    }

    public ProfessionalsDetailsDTO getProfessional(String puid, Users professional) {
       ProfessionalDetails details = professionalsRepository.findProfessionalByUsers(professional).orElseThrow(UserNotFound::new);
       ProfessionalsDetailsDTO returnDTO = professionalsMapper.getProfessionalDetails(professional, details);

       returnDTO.setSkills(skillsService.getSkills(puid));

        return returnDTO;
    }

    public List<ProfessionalsDTO> getAllProfessionalsView() {
        return professionalsViewMapper.listModelToDto(professionalViewRepository.findAll());
    }
}
