package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.UpdateFieldsException;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDto;
import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionalsService {

    //Repositórios
    private final ProfessionalsRepository professionalsRepository;
    private final UsersRepository usersRepository;

    //Mappers
    private final ProfessionalsMapper professionalsMapper;

    //Validações
    private final FindUsersMethods findUsers;

    //Método não salvando no banco de dados
    //TODO corrigir método
    public ProfessionalDetails updateProfessionalDetails(String userTag, ProfessionalsUpdateDto professionalsUpdateDto) {
        Users professionalUser = findUsers.findUserByUserTag(userTag);
        ProfessionalDetails detailsToUpdate = findUsers.findProfessionalByUser(professionalUser);
        ProfessionalDetails updateFields = professionalsMapper.updateProfessionalDtoToModel(professionalsUpdateDto);

        for(Field field : ProfessionalDetails.class.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.get(updateFields) != null && !field.get(updateFields).equals(field.get(detailsToUpdate))) {
                    field.set(detailsToUpdate, field.get(updateFields));
                }
            } catch (IllegalAccessException e) {
                throw new UpdateFieldsException("Não foi possível atualizar os detalhes do profissional.");
            }
        }
        return detailsToUpdate;
    }

    public ProfessionalsDto getProfessional(String userTag) {
        Users user = findUsers.findUserByUserTag(userTag);
        ProfessionalDetails details = findUsers.findProfessionalByUser(user);

        return professionalsMapper.professionalModelToDto(user, details);
    }

    public List<ProfessionalsDto> listAllProfessionals() {
        List<Users> usersList = usersRepository.findByRole(UserRoles.PROFESSIONAL);
        List<ProfessionalDetails> professionalDetailsList = professionalsRepository.findAll(); // Se necessário

        return professionalsMapper.listModelToDto(usersList, professionalDetailsList);
    }

}
