package br.com.listtta.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.listtta.backend.exceptions.UpdateFieldsException;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalDetails;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.model.mapper.ProfessionalsViewMapper;
import br.com.listtta.backend.repository.ProfessionalViewRepository;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.Patcher;
import lombok.RequiredArgsConstructor;

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

    @Transactional
    public ProfessionalDetails createNewProfessionalDetals(UsersSignupDto signupDto) {
        Users professionalUser = findUsers.findUsersByPuid(signupDto.getPuid());
    
        // Mapeando o DTO de detalhes profissionais
        ProfessionalsSignupDto professionalDetails = signupDto.getProfessionalsDto();
    
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
    public ProfessionalDetails updateProfessionalDetails(String userTag, ProfessionalsUpdateDto professionalsUpdateDto) {
        Users professionalUser = findUsers.findUsersByPuid(userTag);
        ProfessionalDetails detailsToUpdate = findUsers.findProfessionalByUser(professionalUser);
        ProfessionalDetails updateFields = professionalsMapper.updateProfessionalDtoToModel(professionalsUpdateDto);

        try {
            Patcher.patch(detailsToUpdate, updateFields);
            professionalsRepository.save(detailsToUpdate);
        } catch (IllegalAccessException e) {
            throw new UpdateFieldsException("Não foi possível atualizar o usuário: " + e);
        }
        return detailsToUpdate;
    }

    public ProfessionalsDto getProfessional(String userTag) {
        Users user = findUsers.findUsersByPuid(userTag);
        ProfessionalDetails details = findUsers.findProfessionalByUser(user);

        return professionalsMapper.professionalModelToDto(user, details);
    }

    public List<ProfessionalsDto> listAllProfessionals() {
        List<Users> usersList = usersRepository.findByRole(UserRoles.PROFESSIONAL);
        List<ProfessionalDetails> professionalDetailsList = professionalsRepository.findAll(); // Se necessário

        return professionalsMapper.listModelToDto(usersList, professionalDetailsList);
    }

    public List<ProfessionalsDto> getAllProfessionalsView() {
        return professionalsViewMapper.listModelToDto(professionalViewRepository.findAll());
    }

}
