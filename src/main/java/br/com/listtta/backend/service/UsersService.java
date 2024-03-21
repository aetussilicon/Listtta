package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.UserNotFound;
import br.com.listtta.backend.model.dto.ProfessionalsSignupDtoComplement;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.model.mapper.UsersMapper;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.validation.CPFValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersMapper mapper;
    private final UsersRepository usersRepository;
    private final ProfessionalsRepository professionalsRepository;
    private final UsernameGenerateService usernameGenerateService;
    private final CPFValidatorService validatorService;
    private final ProfessionalsMapper professionalsMapper;

    private void checkUserInDatabaseByTaxNumberAndThrowError(String taxNumber) {
        Optional<Users> checkingUser = usersRepository.findUserByTaxNumber(taxNumber);

        if (checkingUser.isPresent()) {
            throw new RuntimeException("Usuário já cadastrado.");
        }
    }

    public Users createNewUser(UsersSignupDto usersSignupDto) {
        usersSignupDto.setTaxNumber(
                validatorService.cpfValidation(usersSignupDto.getTaxNumber()));

        checkUserInDatabaseByTaxNumberAndThrowError(usersSignupDto.getTaxNumber());

        usersSignupDto.setUsername(
                usernameGenerateService.usernameGenerator(usersSignupDto.getFullName()));

        Users newUser = mapper.usersSignupDto(usersSignupDto);

        usersRepository.save(newUser);

        if (usersSignupDto.getRole() == UserRoles.PROFESSIONAL) {
            ProfessionalsSignupDtoComplement complement = new ProfessionalsSignupDtoComplement();

            complement.setUsers(newUser);
            complement.setType(usersSignupDto.getType());
            complement.setInstagramUrl(usersSignupDto.getInstagramUrl());

            professionalsRepository.save(professionalsMapper.professionalsDetailsDtoToModel(complement));
        }

        return newUser;
    }

}
