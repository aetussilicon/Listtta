package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.address.NewUserAddressDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.entities.Address;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.mapper.AddressMapper;
import br.com.listtta.backend.repository.AddressRepository;
import br.com.listtta.backend.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepo;
    private final AddressMapper addressMapper;
    private final UsersRepository usersRepo;

    public Address createNewUserAddress(UsersSignupDto userDto) {


        Optional<Users> checkRecentUser = usersRepo.findUserByUserTag(userDto.getUserTag());
        if (checkRecentUser.isPresent()) {
            NewUserAddressDto userAddressDto = new NewUserAddressDto();
            Users user = checkRecentUser.get();
            userAddressDto.setUsers(user);
            userAddressDto.setUserTag(userDto.getUserTag());
            userAddressDto.setState(userDto.getState());
            userAddressDto.setCity(userDto.getCity());
            userAddressDto.setDistrict(userDto.getDistrict());
            userAddressDto.setPostalCode(userDto.getPostalCode());

            return addressRepo.save(addressMapper.newUserAddressDtoToModel(userAddressDto));
        } else {
            throw new RuntimeException();
        }

    }

}
