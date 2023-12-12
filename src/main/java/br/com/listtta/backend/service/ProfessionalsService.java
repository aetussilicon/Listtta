package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.ProfessionalNotFoundException;
import br.com.listtta.backend.exceptions.UpdateFieldsException;
import br.com.listtta.backend.model.Professionals;
import br.com.listtta.backend.model.dto.ProfessionalUpdateDto;
import br.com.listtta.backend.model.dto.ProfessionalsDto;
import br.com.listtta.backend.model.dto.ProfessionalsSignUpDto;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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

    public Professionals patchProfessional(UUID professionalId, ProfessionalUpdateDto professionalUpdateDto){
        Optional<Professionals> checkProfessionalInDatabase = professionalsRepository.findById(professionalId);
        Professionals professionalsToUpdate = checkProfessionalInDatabase.get();
        Professionals updateFields = professionalsMapper.updateDtoToModel(professionalUpdateDto);

        if (checkProfessionalInDatabase.isPresent()){
            for (Field field : Professionals.class.getDeclaredFields()){
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

    public ProfessionalsDto listOneProfessional(UUID professionalId){
        Optional<Professionals> checkProfessionalInDatabase = professionalsRepository.findById(professionalId);
        if (checkProfessionalInDatabase.isPresent()) {
            return professionalsMapper.professionalModeltoDto(checkProfessionalInDatabase.get());
        }
        throw new ProfessionalNotFoundException("Profissional não encontrado");
    }

    public List<ProfessionalsDto> listAllProfessionals(){
        return professionalsMapper.listModelToDto(professionalsRepository.findAll());
    }

    public boolean deleteProfessional(UUID professionalId) {
        Optional<Professionals> chekcInDatabase = professionalsRepository.findById(professionalId);
        if (chekcInDatabase.isPresent()) {
            professionalsRepository.deleteById(professionalId);
            return true;
        } return false;
    }
}
