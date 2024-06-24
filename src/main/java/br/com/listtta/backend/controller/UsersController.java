package br.com.listtta.backend.controller;

import br.com.listtta.backend.exceptions.users.UserNotFound;
import br.com.listtta.backend.model.abstracts.UsersDTOAbstract;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.service.UsersService;
import br.com.listtta.backend.util.validation.ControllersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService service;
    private final ControllersResponse responses;

    @PatchMapping("update/{puid}")
    public ResponseEntity<Map<String, Object>> updateUserInfo(@PathVariable String puid, @RequestBody UsersUpdateDTO updateDTO) {
        try {
            Users updateUser = service.updateUserInfo(puid, updateDTO);
            return new ResponseEntity<>(responses.controllersResponse(updateUser, null), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("update/picture/{puid}")
    public ResponseEntity<Map<String, Object>> updateUserProfilePicture(@PathVariable String puid,
                                                                        @ModelAttribute UsersUpdateDTO usersUpdateDto) {

        try {
            Users updatedUser = service.updateUserProfilePicture(puid, usersUpdateDto);
            return new ResponseEntity<>(responses.controllersResponse(updatedUser, null), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @RequestMapping("list/{puid}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String puid) {
        try {
            UsersDTOAbstract user = service.getUser(puid);
            return new ResponseEntity<>(responses.controllersResponse(user, null), HttpStatus.OK);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
