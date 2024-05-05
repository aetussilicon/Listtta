package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.users.UsersViewDTO;
import br.com.listtta.backend.model.entities.users.UsersView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.swing.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersViewMapper {
    UsersViewMapper INSTANCE = Mappers.getMapper(UsersViewMapper.class);

    UsersViewDTO modelToDto(UsersView usersView);
    List<UsersViewDTO> listModelToDto(List<UsersView> usersViewList);
}
