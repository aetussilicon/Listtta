package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.FileToBigException;
import br.com.listtta.backend.exceptions.MimeTypeNotAllowed;
import br.com.listtta.backend.model.abstracts.UsersDTOAbstract;
import br.com.listtta.backend.model.dto.address.AddressDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDetailsDTO;
import br.com.listtta.backend.model.dto.users.UsersDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
import br.com.listtta.backend.model.entities.address.Address;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.UsersMapper;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.CPFValidatorService;
import br.com.listtta.backend.util.validation.ControllersResponse;
import br.com.listtta.backend.util.validation.Patcher;
import br.com.listtta.backend.util.validation.TokenValidation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UsersService {

    // Mappers
    private final UsersMapper mapper;

    // Repositórios
    private final UsersRepository usersRepository;

    // Services
    private final AddressService addressService;
    private final ProfessionalsService professionalsService;

    // Validações e geradores
    private final PuidGenerator puidGenerator;
    private final FindUsersMethods findUsersMethods;
    private final CPFValidatorService CPFValidation;
    private final TokenValidation tokenValidation;

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    private static final List<String> ALLOWED_MIME_TYPE = Arrays.asList("image/png", "image/jpeg", "image/jpg");

    // Método de cadastro de usuários.
    @Transactional
    public Users createNewUser(UsersSignupDTO usersSignupDto) {

        // Checar se já existe usuário com base no email.
        findUsersMethods.verifyInUserAlredyExists(usersSignupDto.getEmail());

        if (usersSignupDto.getRole() == UserRoles.USER) {
            usersSignupDto.setPuid(puidGenerator.puidGenerator(null));
        } else if (usersSignupDto.getRole() == UserRoles.PROFESSIONAL) {
            usersSignupDto.setPuid(puidGenerator.puidGenerator(usersSignupDto.getProfessionalsDto().getType()));
        }

        // Data de criação da conta.
        usersSignupDto.setCreatedDate(new Date());

        // Criptografa senha do usuário.
        usersSignupDto.setPassword(new BCryptPasswordEncoder().encode(usersSignupDto.getPassword()));

        // Mapeia o usuário para entidade e salva no banco de dados.
        Users newUser = mapper.usersSignupDto(usersSignupDto);
        usersRepository.save(newUser);

        // Após salvar, salva também o endereço do usuário.
        addressService.createNewUserAddress(usersSignupDto);

        if (usersSignupDto.getRole() == UserRoles.PROFESSIONAL) {
            professionalsService.createNewProfessionalDetails(usersSignupDto);
        }
        return newUser;
    }

    // Update de usuários
    @Transactional
    public UsersDTOAbstract updateUserInfo(String puid, UsersUpdateDTO usersUpdateDto) {
        Users userToUpdate = findUsersMethods.findUsersByPuid(puid);
        Users updateFields = mapper.updateDtoToModel(usersUpdateDto);
        UsersDTOAbstract returnDTO = null;

        // if (usersUpdateDto.getTaxNumber() != null) {
        // updateFields.setTaxNumber(CPFValidation.cpfValidation(usersUpdateDto.getTaxNumber()));
        // }

        try {
            Patcher.patch(userToUpdate, updateFields);

            // Salvar o usuário atualizado no repositório
            usersRepository.save(userToUpdate);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if (usersUpdateDto.getAddress() != null) {
            addressService.updateUserAddress(puid, usersUpdateDto);
        }

        if (userToUpdate.getRole() == UserRoles.USER) {
            returnDTO = mapper.userModelToDto(userToUpdate);
        } else if (userToUpdate.getRole() == UserRoles.PROFESSIONAL
                && usersUpdateDto.getProfessionalsDetails() != null) {
            returnDTO = professionalsService.updateProfessionalDetails(puid, usersUpdateDto);
        } else if (usersUpdateDto.getProfessionalsDetails() == null) {
            returnDTO = professionalsService.getProfessional(puid, userToUpdate);
        }

        return returnDTO;
    }

    @Transactional
    public Users updateUserProfilePicture(String puid, UsersUpdateDTO updateDTO) {
        Users userToUpdate = findUsersMethods.findUsersByPuid(puid);
        MultipartFile profilePicture = updateDTO.getProfilePicture();
        String fileType = profilePicture.getContentType();

        if (profilePicture != null && !profilePicture.isEmpty()) {
            if (profilePicture.getSize() > MAX_FILE_SIZE) {
                throw new FileToBigException();
            } else if (!ALLOWED_MIME_TYPE.contains(fileType)) {
                throw new MimeTypeNotAllowed(fileType);
            }

            try {
                byte[] bytes = profilePicture.getBytes();
                userToUpdate.setProfilePicture(bytes);
                usersRepository.save(userToUpdate);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return userToUpdate;
    }

    public UsersDTOAbstract getUser(String puid, HttpServletRequest request) {
        tokenValidation.compareTokens(puid, request);

        Tika tika = new Tika();

        Users checkedUser = findUsersMethods.findUsersByPuid(puid);
        Address getUserAddres = findUsersMethods.findUserAddress(puid);
        AddressDTO userAddress = new AddressDTO();
        userAddress.setState(getUserAddres.getState());
        userAddress.setCity(getUserAddres.getCity());
        userAddress.setCityZone(getUserAddres.getCityZone() != null ? getUserAddres.getCityZone().getCityZone() : "");
        userAddress.setDistrict(getUserAddres.getDistrict());
        userAddress.setStreet(getUserAddres.getStreet());
        userAddress.setComplement(getUserAddres.getComplement());
        userAddress.setZipCode(getUserAddres.getZipCode());

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
}