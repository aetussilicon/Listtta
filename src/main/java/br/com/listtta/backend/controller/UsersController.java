package br.com.listtta.backend.controller;

import br.com.listtta.backend.exceptions.UserNotAllowedException;
import br.com.listtta.backend.exceptions.users.UserNotFound;
import br.com.listtta.backend.model.abstracts.UsersDTOAbstract;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.service.UsersService;
import br.com.listtta.backend.util.validation.ControllersResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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

    @PatchMapping
    @RequestMapping("update/{puid}")
    public ResponseEntity<Map<String, Object>> updateUserInfo(@PathVariable String puid,
            @RequestBody @Valid UsersUpdateDTO updateDTO) {
        try {
            UsersDTOAbstract updateUser = service.updateUserInfo(puid, updateDTO);
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
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String puid, HttpServletRequest request) {
        try {
            UsersDTOAbstract user = service.getUser(puid, request);
            return new ResponseEntity<>(responses.controllersResponse(user, null), HttpStatus.OK);
        } catch (UserNotAllowedException e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.FORBIDDEN);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
