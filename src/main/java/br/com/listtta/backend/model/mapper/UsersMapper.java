package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.address.NewUserAddressDto;
import br.com.listtta.backend.model.dto.users.UsersDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.dto.users.UsersUpdateDto;
import br.com.listtta.backend.model.entities.Address;
import br.com.listtta.backend.model.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    @Mapping(target = "userId", expression = "java(UUID.randomUUID())")
    Users usersSignupDto(UsersSignupDto usersSignupDto);

    @Mapping(target = "userId", ignore = true)
    Users updateDtoToModel(UsersUpdateDto userUpdateDto);

    UsersDto userModelToDto(Users users);

    List<UsersDto> listModelToDto(List<Users> usersList);
}
