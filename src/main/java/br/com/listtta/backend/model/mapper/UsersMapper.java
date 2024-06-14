package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.users.UsersDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
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
    @Mapping(target = "userTag", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "taxNumber", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "whatsappContact", ignore = true)
    @Mapping(target = "userGender", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Users usersSignupDto(UsersSignupDTO usersSignupDto);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "puid", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Users updateDtoToModel(UsersUpdateDTO userUpdateDto);

    UsersDTO userModelToDto(Users users);

    List<UsersDTO> listModelToDto(List<Users> usersList);
}
