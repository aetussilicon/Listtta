package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.UpdateFieldsException;
import br.com.listtta.backend.model.dto.address.NewUserAddressDto;
import br.com.listtta.backend.model.dto.address.UpdateUserAddressDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDtoComplement;
import br.com.listtta.backend.model.dto.users.UsersDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.dto.users.UsersUpdateDto;
import br.com.listtta.backend.model.entities.Address;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.model.mapper.AddressMapper;
import br.com.listtta.backend.model.mapper.ProfessionalsMapper;
import br.com.listtta.backend.model.mapper.UsersMapper;
import br.com.listtta.backend.repository.AddressRepository;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.CPFValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
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
    private final FindUsersMethods findUsersMethods;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    public Users createNewUser(UsersSignupDto usersSignupDto) {
        usersSignupDto.setTaxNumber(
                validatorService.cpfValidation(usersSignupDto.getTaxNumber()));

        findUsersMethods.findUserByTaxNumberAndThrowErro(usersSignupDto.getTaxNumber());

       usersSignupDto.setUserTag(
                usernameGenerateService.usernameGenerator(usersSignupDto.getFullName()));

       usersSignupDto.setPassword(new BCryptPasswordEncoder().encode(usersSignupDto.getPassword()));

        Users newUser = mapper.usersSignupDto(usersSignupDto);

        usersRepository.save(newUser);

//        if (usersSignupDto.getRole() == UserRoles.PROFESSIONAL) {
//            ProfessionalsSignupDtoComplement complement = new ProfessionalsSignupDtoComplement();
//
//            complement.setUsers(newUser);
//            complement.setType(usersSignupDto.getType());
//            complement.setInstagramUrl(usersSignupDto.getInstagramUrl());
//
//            professionalsRepository.save(professionalsMapper.professionalsDetailsDtoToModel(complement));
//        }

        return newUser;
    }

    public Users updateUser(String userTag, UsersUpdateDto usersUpdateDto) {
        Users userToUpdate = findUsersMethods.findUserByUserTag(userTag);
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

        NewUserAddressDto address = new NewUserAddressDto();
       address.setAddressId(userToUpdate.getUserAddress().getAddressId().longValue());



//        Optional<Address> userAddress = addressRepository.findById(userAddressId);
//        if (userAddress.isPresent()) {
//            UpdateUserAddressDto updateAddress = new UpdateUserAddressDto();
//            updateAddress.setCity(updateFields.getUserAddress().getCity());
//            updateAddress.setDistrict(updateFields.getUserAddress().getDistrict());
//            updateAddress.setState(updateFields.getUserAddress().getState());
//            updateAddress.setPostalCode(updateFields.getUserAddress().getPostalCode());
//
//            for (Field field : Address.class.getDeclaredFields()) {
//                field.setAccessible(true);
//
//                try {
//                    if (field.get(updateAddress) != null && !field.get(updateAddress).equals(field.get(userAddress.get()))) {
//                        field.set(userAddress.get(), field.get(updateAddress));
//                    }
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException();
//                }
//            }

//        }

        return userToUpdate;
    }


    public UsersDto getUser(String userTag) {
        return mapper.userModelToDto(findUsersMethods.findUserByUserTag(userTag));
    }

    public List<UsersDto> getAllUsers() {
       return mapper.listModelToDto(usersRepository.findAll());
    }
}