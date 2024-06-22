package br.com.listtta.backend.service;

import br.com.listtta.backend.model.abstracts.UsersDTOAbstract;
import br.com.listtta.backend.model.dto.address.AddressDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDetailsDTO;
import br.com.listtta.backend.model.dto.users.UsersDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalDetails;
import br.com.listtta.backend.model.entities.address.Address;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.model.mapper.ProfessionalsViewMapper;
import br.com.listtta.backend.model.mapper.UsersMapper;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.Patcher;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
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

            // Salvar o usuário atualizado no repositóriupdateUserInfoo
            usersRepository.save(userToUpdate);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if (userToUpdate.getRole() != UserRoles.USER) {
            professionalsService.updateProfessionalDetails(puid, usersUpdateDto);
        }

        if (usersUpdateDto.getAddress() != null) {
            addressService.updateUserAddress(puid, usersUpdateDto);
        }
        return userToUpdate;
    }

    public UsersDTOAbstract getUser(String puid) {
        Tika tika = new Tika();

        Users checkedUser = findUsersMethods.findUsersByPuid(puid);
        Address getUserAddres = findUsersMethods.findUserAddress(puid);
        AddressDTO userAddress = new AddressDTO();
        userAddress.setState(getUserAddres.getState());
        userAddress.setCity(getUserAddres.getCity());

        UsersDTOAbstract returnDTO = null;

        switch (checkedUser.getRole()) {
            case USER:
                UsersDTO userReturn = mapper.userModelToDto(checkedUser);
                userReturn.setAddress(userAddress);

                if (checkedUser.getProfilePicture() != null) {
                    userReturn.setProfilePicture(Base64.getEncoder()
                            .encodeToString(checkedUser.getProfilePicture()));
                    userReturn.setProfilePictureMimeType(tika.detect(checkedUser.getProfilePicture()));
                }
                 returnDTO = userReturn;
                break;
            case PROFESSIONAL:
               ProfessionalsDetailsDTO professionalsDTO = professionalsService.getProfessional(puid, checkedUser);
               professionalsDTO.setAddress(userAddress);

               if (checkedUser.getProfilePicture() != null) {
                   professionalsDTO.setProfilePicture(Base64.getEncoder()
                           .encodeToString(checkedUser.getProfilePicture()));
                   professionalsDTO.setProfilePictureMimeType(tika.detect(checkedUser.getProfilePicture()));
               }

               returnDTO = professionalsDTO;
               break;
            default:
                throw new RuntimeException();
        }
        return returnDTO;
    }

    public List<UsersDTO> getAllUsers() {
        return mapper.listModelToDto(usersRepository.findAll());
    }
}