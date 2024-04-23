package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.users.UsersDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.dto.users.UsersUpdateDto;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.UsersMapper;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.CPFValidatorService;
import br.com.listtta.backend.util.validation.DateFormatter;
import br.com.listtta.backend.util.validation.Patcher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    //Mappers
    private final UsersMapper mapper;

    //Repositórios
    private final UsersRepository usersRepository;

    //Services
    private final AddressService addressService;
    private final ProfessionalsService professionalsService;

    //Métodos extras
    private final UsernameGenerateService usernameGenerateService;
    private final CPFValidatorService validatorService;
    private final FindUsersMethods findUsersMethods;
    private final DateFormatter dateFormatter;

    //Método de cadastro de usuários.
    public Users createNewUser(UsersSignupDto usersSignupDto) {

        //Valida o CPF do usuário e formata
        usersSignupDto.setTaxNumber(validatorService.cpfValidation(usersSignupDto.getTaxNumber()));

        //Verifica se não já não existe um usuário com esse CPF.
        findUsersMethods.findUserByTaxNumberAndThrowErro(usersSignupDto.getTaxNumber());

        usersSignupDto.setCreatedDate(new Date());

        //Criptografa senha do usuário.
        usersSignupDto.setPassword(new BCryptPasswordEncoder().encode(usersSignupDto.getPassword()));

        //Mapeia o usuário para entidade e salva no banco de dados.
        Users newUser = mapper.usersSignupDto(usersSignupDto);
        usersRepository.save(newUser);

        //Após salvar, salva também o endereço do usuário.
        addressService.createNewUserAddress(usersSignupDto);

        if (usersSignupDto.getRole() == UserRoles.PROFESSIONAL) {
            professionalsService.createNewProfessionalDetals(usersSignupDto);
        }

        return newUser;
    }

    public Users updateUser(String userTag, UsersUpdateDto usersUpdateDto) {
        Users userToUpdate = findUsersMethods.findUserByUserTag(userTag);
        Users updateFields = mapper.updateDtoToModel(usersUpdateDto);

        try {
            Patcher.patch(userToUpdate, updateFields);
            usersRepository.save(userToUpdate);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return userToUpdate;
    }

    public UsersDto getUser(String userTag) {
        return mapper.userModelToDto(findUsersMethods.findUserByUserTag(userTag));
    }

    public List<UsersDto> getAllUsers() {
        return mapper.listModelToDto(usersRepository.findAll());
    }
}