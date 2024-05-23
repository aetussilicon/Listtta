package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.users.UsersDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.dto.users.UsersUpdateDto;
import br.com.listtta.backend.model.entities.users.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    @Mapping(target = "userId", expression = "java(UUID.randomUUID())")
//    @Mapping(target = "userTag", ignore = true)
//    @Mapping(target = "fullName", ignore = true)
//    @Mapping(target = "taxNumber", ignore = true)
//    @Mapping(target = "phoneNumber", ignore = true)
//    @Mapping(target = "whatsappContact", ignore = true)
//    @Mapping(target = "userGender", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Users usersSignupDto(UsersSignupDto usersSignupDto);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "puid", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Users updateDtoToModel(UsersUpdateDto userUpdateDto);

    UsersDto userModelToDto(Users users);

    List<UsersDto> listModelToDto(List<Users> usersList);
}
