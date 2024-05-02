package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.UpdateFieldsException;
import br.com.listtta.backend.model.dto.ProfessionalsRequestedParamsDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.entities.ProfessionalsViewMapper;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import br.com.listtta.backend.model.enums.UserGender;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
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

    //Validações
    private final FindUsersMethods findUsers;
    private final Patcher patcher;

    @Transactional
    public ProfessionalDetails createNewProfessionalDetals(UsersSignupDto signupDto) {
        Users professionalUser = findUsers.findUsersByPuid(signupDto.getPuid());

        ProfessionalsSignupDto professionalDetails = signupDto.getProfessionalsDto();

        professionalDetails.setUsers(professionalUser);
        professionalDetails.setPuid(signupDto.getPuid());

        return professionalsRepository.save(professionalsMapper.professionalsDetailsDtoToModel(professionalDetails));
    }

    //Método não salvando no banco de dados
    //TODO corrigir método
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

    public List<ProfessionalsDto> getProfessionalsByFilters(ProfessionalsRequestedParamsDTO professionalsRequestedParams) {
        UserGender userGender = professionalsRequestedParams.getUserGender();
        String city = professionalsRequestedParams.getCity();
        String state = professionalsRequestedParams.getState();
        ProfessionalsType type = professionalsRequestedParams.getType();

        return professionalsViewMapper.listModelToDto(professionalViewRepository.findAllByQueryParameters(userGender, city, state, type));
    }

}
