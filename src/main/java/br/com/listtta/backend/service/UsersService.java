package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.users.UsersDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.dto.users.UsersUpdateDto;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.UsersMapper;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.CPFValidatorService;
import br.com.listtta.backend.util.validation.Patcher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final PuidGenerator puidGenerator;
    private final CPFValidatorService validatorService;
    private final FindUsersMethods findUsersMethods;

//   Método de cadastro de usuários.
    @Transactional
    public Users createNewUser(UsersSignupDto usersSignupDto) {

        //Checar se já existe usuário com base no email.
        findUsersMethods.findUserByEmail(usersSignupDto.getEmail());

        if (usersSignupDto.getRole() == UserRoles.USER) {
            usersSignupDto.setPuid(puidGenerator.puidGenerator(null));
        } else if (usersSignupDto.getRole() == UserRoles.PROFESSIONAL) {
            usersSignupDto.setPuid(puidGenerator.puidGenerator(usersSignupDto.getProfessionalsDto().getType()));
        }
        //Data de criação da conta.
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


    @Transactional
    public Users updateUser(String puid, UsersUpdateDto usersUpdateDto) {
        Users userToUpdate = findUsersMethods.findUsersByPuid(puid);
        Users updateFields = mapper.updateDtoToModel(usersUpdateDto);

        try {
            Patcher.patch(userToUpdate, updateFields);
            usersRepository.save(userToUpdate);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if (usersUpdateDto.getAddress() != null) {
            addressService.updateUserAddress(puid, usersUpdateDto);
        }

        return userToUpdate;
    }

    public UsersDto getUser(String userTag) {
        return mapper.userModelToDto(findUsersMethods.findUsersByPuid(userTag));
    }

    public List<UsersDto> getAllUsers() {
        return mapper.listModelToDto(usersRepository.findAll());
    }
}