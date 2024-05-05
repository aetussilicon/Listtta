package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.address.NewUserAddressDto;
import br.com.listtta.backend.model.dto.address.UpdateUserAddressDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.dto.users.UsersUpdateDto;
import br.com.listtta.backend.model.entities.Address;
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
    private final UsersRepository usersRepo;
    private final FindUsersMethods findUsers;

    @Transactional
    public Address createNewUserAddress(UsersSignupDto userDto) {
        Optional<Users> checkRecentUser = usersRepo.findUserByPuid(userDto.getPuid());
        if (checkRecentUser.isPresent()) {
            NewUserAddressDto userAddressDto = new NewUserAddressDto();
            Users user = checkRecentUser.get();
            userAddressDto.setUsers(user);
            userAddressDto.setPuid(userDto.getPuid());
            userAddressDto.setState(userDto.getAddress().getState());
            userAddressDto.setCity(userDto.getAddress().getCity());

            return addressRepo.save(addressMapper.newUserAddressDtoToModel(userAddressDto));
        } else {
            throw new RuntimeException();
        }

    }

    public Address updateUserAddress(String puid, UsersUpdateDto updateDto) {
        Address checkInDB = findUsers.findUserAddress(puid);

        UpdateUserAddressDto newDto = new UpdateUserAddressDto();
        newDto.setState(updateDto.getAddress().getState());
        newDto.setCity(updateDto.getAddress().getCity());

        Address updateFields = addressMapper.updateUserAddressDtoToModel(newDto);
        try {
            Patcher.patch(checkInDB, updateFields);
            addressRepo.save(checkInDB);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.fillInStackTrace());
        }
        return checkInDB;
    }

}
