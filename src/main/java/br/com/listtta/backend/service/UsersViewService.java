package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.UserNotFound;
import br.com.listtta.backend.model.dto.users.UsersViewDTO;
import br.com.listtta.backend.model.entities.users.UsersView;
import br.com.listtta.backend.model.mapper.UsersViewMapper;
import br.com.listtta.backend.repository.UsersViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersViewService {

    //Mapper
    private final UsersViewMapper viewMapper;

    //Repositório
    private final UsersViewRepository viewRepository;


    public UsersViewDTO getFullUserDetails(String puid) {
        Optional<UsersView> checkUserInDB = viewRepository.findUsersViewByPuid(puid);
        if (checkUserInDB.isPresent()) {
            return viewMapper.modelToDto(checkUserInDB.get());
        } throw new UserNotFound();
    }
}
