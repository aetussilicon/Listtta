package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.address.CannotSaveAddressException;
import br.com.listtta.backend.model.dto.address.NewUserAddressDTO;
import br.com.listtta.backend.model.dto.address.UpdateUserAddressDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
import br.com.listtta.backend.model.entities.address.Address;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.mapper.AddressMapper;
import br.com.listtta.backend.repository.AddressRepository;
import br.com.listtta.backend.repository.UsersRepository;
import br.com.listtta.backend.util.FindUsersMethods;
import br.com.listtta.backend.util.validation.Patcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepo;
    private final AddressMapper addressMapper;
    private final FindUsersMethods findUsers;

    @Transactional
    public void createNewUserAddress(UsersSignupDTO userDto) {
        Users newUser = findUsers.findUsersByPuid(userDto.getPuid());
        try {
            NewUserAddressDTO userAddressDto = userDto.getAddressDto();
            userAddressDto.setUsers(newUser);
            userAddressDto.setPuid(userDto.getPuid());

            addressRepo.save(addressMapper.newUserAddressDtoToModel(userAddressDto));
        } catch (RuntimeException e) {
            throw new CannotSaveAddressException();
        }

    }

    public void updateUserAddress(String puid, UsersUpdateDTO updateDto) {
        Address checkInDB = findUsers.findUserAddress(puid);

        UpdateUserAddressDTO newDto = new UpdateUserAddressDTO();
        newDto.setState(updateDto.getAddress().getState());
        newDto.setCity(updateDto.getAddress().getCity());

        Address updateFields = addressMapper.updateUserAddressDtoToModel(newDto);
        try {
            Patcher.patch(checkInDB, updateFields);
            addressRepo.save(checkInDB);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.fillInStackTrace());
        }
    }

}
