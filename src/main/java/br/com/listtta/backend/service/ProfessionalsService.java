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

    public Professionals createNewProfessional(ProfessionalsSignUpDto professionalsSignUpDto) {
        professionalsSignUpDto.setUsername(
                usernameGenerateService.usernameGenerator(professionalsSignUpDto.getFullName()));

        return professionalsRepository.save(professionalsMapper.signUpDtoToModel(professionalsSignUpDto));
    }

    public Professionals patchProfessional(UUID userId, ProfessionalUpdateDto professionalUpdateDto){
        Optional<Professionals> checkProfessionalInDatabase = professionalsRepository.findById(userId);
        Professionals professionalsToUpdate = checkProfessionalInDatabase.get();
        Professionals updateFields = professionalsMapper.updateDtoToModel(professionalUpdateDto);

        if (checkProfessionalInDatabase.isPresent()){
            for (Field field : Professionals.class.getSuperclass().getDeclaredFields()) {
                field.setAccessible(true);

                try {
                    if (field.get(updateFields) != null && !field.get(updateFields).equals(field.get(professionalsToUpdate))) {
                        field.set(professionalsToUpdate, field.get(updateFields));
                    }
                } catch (IllegalAccessException e){
                    throw new UpdateFieldsException("Não foi possível atualizar o profissional");
                }
            }
        }
        return professionalsToUpdate;
    }

    public ProfessionalsDto listOneProfessional(UUID userId){
        Optional<Professionals> checkProfessionalInDatabase = professionalsRepository.findById(userId);
        if (checkProfessionalInDatabase.isPresent()) {
            return professionalsMapper.professionalModeltoDto(checkProfessionalInDatabase.get());
        }
        throw new UserNotFound("Profissional não encontrado");
    }

    public List<ProfessionalsDto> listAllProfessionals(){
        return professionalsMapper.listModelToDto(professionalsRepository.findAll());
    }

    public boolean deleteProfessional(UUID userId) {
        Optional<Professionals> chekcInDatabase = professionalsRepository.findById(userId);
        if (chekcInDatabase.isPresent()) {
            professionalsRepository.deleteById(userId);
            return true;
        } return false;
    }
}
