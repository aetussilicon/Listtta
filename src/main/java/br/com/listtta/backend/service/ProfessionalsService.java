package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessionalsService {

    private final ProfessionalsRepository professionalsRepository;
    private final UsersRepository usersRepository;
    private final ProfessionalsMapper professionalsMapper;
    private final FindUsersMethods findUsers;

    public ProfessionalsDto getProfessional(String username) {
        Users user = findUsers.findUserByUsername(username);
        ProfessionalDetails details = findUsers.findProfessionalByUser(user);

        return professionalsMapper.professionalModelToDto(user, details);
    }

}
