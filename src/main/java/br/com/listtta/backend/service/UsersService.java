package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.files.FileToBigException;
import br.com.listtta.backend.exceptions.files.MimeTypeNotAllowedException;
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
import br.com.listtta.backend.util.validation.*;
import br.com.listtta.backend.util.validation.PhoneNumberFormatter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * Este serviço lida com operações como registro de novos usuários, atualização de dados e imagens de perfil, e
 * obtenção de informações de usuários.
 */
@Service
@RequiredArgsConstructor
public class UsersService {

    // Mappers para conversão entre DTOs e entidades
    private final UsersMapper mapper;

    // Repositórios para interação com o banco de dados
    private final UsersRepository usersRepository;

    // Outros serviços utilizados
    private final AddressService addressService;
    private final ProfessionalsService professionalsService;

    // Utilitários para validação e geração de dados
    private final PuidGenerator puidGenerator;
    private final FindUsersMethods findUsersMethods;
    private final CPFValidatorService CPFValidation;
    private final TokenValidation tokenValidation;
    private final UpdateFieldsService updateService;

    // Configurações para upload de arquivos
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024; //5MB
    private static final List<String> ALLOWED_MIME_TYPE = Arrays.asList("image/png", "image/jpeg", "image/jpg");

    /**
     * Cria um novo usuário com base nas informações fornecidas.
     *
     * @param usersSignupDto DTO contendo as informações para o cadastro do novo usuário.
     * @return A entidade de usuário recém-criada.
     */
    @Transactional
    public Users createNewUser(UsersSignupDTO usersSignupDto) {

        // Verifica se já existe um usuário com o mesmo e-mail
        findUsersMethods.verifyInUserAlredyExists(usersSignupDto.getEmail());

        // Gera um PUID baseado no tipo de usuário
        if (usersSignupDto.getRole() == UserRoles.USER) {
            usersSignupDto.setPuid(puidGenerator.puidGenerator(null));
        } else if (usersSignupDto.getRole() == UserRoles.PROFESSIONAL) {
            usersSignupDto.setPuid(puidGenerator.puidGenerator(usersSignupDto.getProfessionalsDto().getType()));
        }

        // Define a data de criação da conta e criptografa a senha
        usersSignupDto.setCreatedDate(new Date());
        usersSignupDto.setPassword(new BCryptPasswordEncoder().encode(usersSignupDto.getPassword()));

        // Mapeia o DTO para a entidade e salva no banco de dados
        Users newUser = mapper.usersSignupDto(usersSignupDto);
        usersRepository.save(newUser);

        // Salva o endereço e detalhes profissionais, se aplicável
        addressService.createNewUserAddress(usersSignupDto);
        if (usersSignupDto.getRole() == UserRoles.PROFESSIONAL) {
            professionalsService.createNewProfessionalDetails(usersSignupDto);
        }
        return newUser;
    }

    /**
     * Atualiza as informações de um usuário existente.
     *
     * @param puid Identificador do usuário a ser atualizado.
     * @param usersUpdateDto DTO contendo as novas informações do usuário.
     * @param request Solicitação HTTP para validação de token.
     * @return Um DTO contendo as informações atualizadas do usuário.
     */
    @Transactional
    public UsersDTOAbstract updateUserInfo(String puid, UsersUpdateDTO usersUpdateDto, HttpServletRequest request) {
        // Valida o token da solicitação
        tokenValidation.compareTokens(puid, request);

        // Busca o usuário e atualiza os campos
        Users userToUpdate = findUsersMethods.findUsersByPuid(puid);
        Users updateFields = mapper.updateDtoToModel(usersUpdateDto);
        UsersDTOAbstract returnDTO = null;

        // Atualiza campos específicos do usuário
        updateService.updateUserPhoneNumber(usersUpdateDto, updateFields);
        updateService.updateUserWhatsappContact(usersUpdateDto, updateFields);

        try {
            // Salvar o usuário atualizado no repositório
            Patcher.patch(userToUpdate, updateFields);
            usersRepository.save(userToUpdate);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        // Atualiza campos do endereço, se aplicável
        updateService.updateUserAddressFields(puid, usersUpdateDto);

        // Determina o tipo de DTO a ser retornado
        return determinateReturnDTO(puid, usersUpdateDto, userToUpdate);
    }

    /**
     * Atualiza a imagem de perfil de um usuário.
     *
     * @param puid Identificador do usuário cuja imagem de perfil será atualizada.
     * @param updateDTO DTO contendo a nova imagem de perfil.
     * @param request Solicitação HTTP para validação de token.
     * @return O usuário com a imagem de perfil atualizada.
     */
    @Transactional
    public Users updateUserProfilePicture(String puid, UsersUpdateDTO updateDTO, HttpServletRequest request) {
        // Valida o token da solicitação
        tokenValidation.compareTokens(puid, request);

        // Busca o usuário e valida a imagem de perfil
        Users userToUpdate = findUsersMethods.findUsersByPuid(puid);
        MultipartFile profilePicture = updateDTO.getProfilePicture();
        String fileType = profilePicture.getContentType();

        if (!profilePicture.isEmpty()) {
            if (profilePicture.getSize() > MAX_FILE_SIZE) {
                throw new FileToBigException();
            } else if (!ALLOWED_MIME_TYPE.contains(fileType)) {
                throw new MimeTypeNotAllowedException(fileType);
            }

            try {
                // Atualiza a imagem de perfil do usuário
                byte[] bytes = profilePicture.getBytes();
                userToUpdate.setProfilePicture(bytes);
                usersRepository.save(userToUpdate);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return userToUpdate;
    }

    /**
     * Obtém as informações de um usuário específico.
     *
     * @param puid Identificador do usuário a ser recuperado.
     * @param request Solicitação HTTP para validação de token.
     * @return Um DTO contendo as informações do usuário.
     */
    public UsersDTOAbstract getUser(String puid, HttpServletRequest request) {
        // Valida o token da solicitação
        tokenValidation.compareTokens(puid, request);

        // Inicializa o detector de tipo de arquivo
        Tika tika = new Tika();

        // Busca o usuário e seu endereço
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

        // Prepara o DTO de retorno com base no tipo de usuário
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

    /**
     * Determina o tipo de DTO a ser retornado após uma atualização.
     *
     * @param puid Identificador do usuário.
     * @param usersUpdateDTO DTO contendo as informações atualizadas.
     * @param userToUpdate Entidade do usuário a ser retornado.
     * @return O DTO correspondente com base no tipo de usuário.
     */
    private UsersDTOAbstract determinateReturnDTO(String puid, UsersUpdateDTO usersUpdateDTO, Users userToUpdate) {
        if (userToUpdate.getRole() == UserRoles.USER) {
            return mapper.userModelToDto(userToUpdate);
        } else if (userToUpdate.getRole() == UserRoles.PROFESSIONAL) {
            if (usersUpdateDTO.getProfessionalsDetails() != null) {
                return professionalsService.updateProfessionalDetails(puid, usersUpdateDTO);
            } else {
                return professionalsService.getProfessional(puid, userToUpdate);
            }
        }
        return null;
    }
}