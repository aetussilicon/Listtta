package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.ProfessionalNotFoundException;
import br.com.listtta.backend.model.Professionals;
import br.com.listtta.backend.model.dto.ProfessionalsDto;
import br.com.listtta.backend.model.dto.ProfessionalsSignUpDto;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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

    public ProfessionalsDto listOneProfessional(UUID professionalId){
        Optional<Professionals> checkProfessionalInDatabase = professionalsRepository.findById(professionalId);
        if (checkProfessionalInDatabase.isPresent()) {
            return professionalsMapper.professionalModeltoDto(checkProfessionalInDatabase.get());
        }
        throw new ProfessionalNotFoundException("Profissional não encontrado");
    }
}
