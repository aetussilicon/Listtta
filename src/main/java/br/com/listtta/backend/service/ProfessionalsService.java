package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.UpdateFieldsException;
import br.com.listtta.backend.exceptions.UserNotFound;
import br.com.listtta.backend.model.dto.professionals.ProfessionalUpdateDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignUpDto;
import br.com.listtta.backend.model.entities.Professionals;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfessionalsService {

    private final ProfessionalsRepository professionalsRepository;
    private final UsernameGenerateService usernameGenerateService;
    private final ProfessionalsMapper professionalsMapper;
    Professionals checkedProfessional;

    private Professionals checkProfessionalInDatabase(UUID userId) {
        Optional<Professionals> checkProfessional = professionalsRepository.findById(userId);

        if (checkProfessional.isPresent()) {
            return checkProfessional.get();
        }
        throw new UserNotFound("Usuário não encontrado!");
    }

    public Professionals createNewProfessional(ProfessionalsSignUpDto professionalsSignUpDto) {
        professionalsSignUpDto.setUsername(
                usernameGenerateService.usernameGenerator(professionalsSignUpDto.getFullName()));

        return professionalsRepository.save(professionalsMapper.signUpDtoToModel(professionalsSignUpDto));
    }

    public Professionals patchProfessional(UUID userId, ProfessionalUpdateDto professionalUpdateDto) {
        checkedProfessional = checkProfessionalInDatabase(userId);
        Professionals updateFields = professionalsMapper.updateDtoToModel(professionalUpdateDto);

        for (Field field : Professionals.class.getSuperclass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.get(updateFields) != null && !field.get(updateFields).equals(field.get(checkedProfessional))) {
                    field.set(checkedProfessional, field.get(updateFields));
                }
            } catch (IllegalAccessException e) {
                throw new UpdateFieldsException("Não foi possível atualizar o profissional");
            }
        }
        return checkedProfessional;
    }

    public ProfessionalsDto listOneProfessional(UUID userId) {
        return professionalsMapper.professionalModelToDto(checkProfessionalInDatabase(userId));
    }

    public List<ProfessionalsDto> listAllProfessionals() {
        return professionalsMapper.listModelToDto(professionalsRepository.findAll());
    }

    public String deleteProfessional(UUID userId) {
        checkProfessionalInDatabase(userId);
        professionalsRepository.deleteById(userId);
        return "Usuário deletado com sucesso";
    }
}
