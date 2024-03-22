package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDtoComplement;
import br.com.listtta.backend.model.dto.users.UsersDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.model.mapper.UsersMapper;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.CPFValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersMapper mapper;
    private final UsersRepository usersRepository;
    private final ProfessionalsRepository professionalsRepository;
    private final UsernameGenerateService usernameGenerateService;
    private final CPFValidatorService validatorService;
    private final ProfessionalsMapper professionalsMapper;
    private final FindUsersMethods findUsersMethods;

    public Users createNewUser(UsersSignupDto usersSignupDto) {
        usersSignupDto.setTaxNumber(
                validatorService.cpfValidation(usersSignupDto.getTaxNumber()));

        findUsersMethods.findUserByTaxNumberAndThrowErro(usersSignupDto.getTaxNumber());

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

    public UsersDto getUser(String username) {
        return mapper.userModelToDto(findUsersMethods.findUserByUsername(username));
    }
}