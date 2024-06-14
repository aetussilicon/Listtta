package br.com.listtta.backend.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.listtta.backend.model.dto.users.UsersDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.UsersMapper;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.Patcher;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

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
    private final FindUsersMethods findUsersMethods;

    // Método de cadastro de usuários.
    @Transactional
    public Users createNewUser(UsersSignupDTO usersSignupDto) {

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
    
    //Update de usuários
    @Transactional
    public Users updateUser(String puid, UsersUpdateDTO usersUpdateDto) {
        Users userToUpdate = findUsersMethods.findUsersByPuid(puid);
        Users updateFields = mapper.updateDtoToModel(usersUpdateDto);

        try {
            Patcher.patch(userToUpdate, updateFields);

            //Atualizar a foto de perfil, se fornecida
            MultipartFile profilePicture = usersUpdateDto.getProfilePicture();
            if (profilePicture != null && !profilePicture.isEmpty()) {
                try {
                    byte[] bytes = profilePicture.getBytes();

                    userToUpdate.setProfilePicture(bytes);
                }
                 catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            // Salvar o usuário atualizado no repositório
            usersRepository.save(userToUpdate);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if (usersUpdateDto.getAddress() != null) {
            addressService.updateUserAddress(puid, usersUpdateDto);
        }
        return userToUpdate;
    }

    public UsersDTO getUser(String userTag) {
        return mapper.userModelToDto(findUsersMethods.findUsersByPuid(userTag));
    }

    public List<UsersDTO> getAllUsers() {
        return mapper.listModelToDto(usersRepository.findAll());
    }
}