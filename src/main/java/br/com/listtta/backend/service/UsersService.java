package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.UpdateFieldsException;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDtoComplement;
import br.com.listtta.backend.model.dto.users.UsersDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.dto.users.UsersUpdateDto;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.model.mapper.UsersMapper;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.CPFValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

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

       usersSignupDto.setPassword(new BCryptPasswordEncoder().encode(usersSignupDto.getPassword()));

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

    public Users updateUser(String username, UsersUpdateDto usersUpdateDto) {
        Users userToUpdate = findUsersMethods.findUserByUsername(username);
        Users updateFields = mapper.updateDtoToModel(usersUpdateDto);

        for(Field field : Users.class.getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.get(updateFields) != null && !field.get(updateFields).equals(field.get(userToUpdate))) {
                    field.set(userToUpdate, field.get(updateFields));
                }
            } catch (IllegalAccessException e) {
                throw new UpdateFieldsException("Não foi possível atualizar o usuário");
            }
        }

        return userToUpdate;
    }


    public UsersDto getUser(String username) {
        return mapper.userModelToDto(findUsersMethods.findUserByUsername(username));
    }

    public List<UsersDto> getAllUsers() {
       return mapper.listModelToDto(usersRepository.findAll());
    }
}