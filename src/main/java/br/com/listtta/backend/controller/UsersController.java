package br.com.listtta.backend.controller;

import br.com.listtta.backend.exceptions.UserNotFound;
import br.com.listtta.backend.model.dto.users.UsersDTO;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.service.UsersService;
import br.com.listtta.backend.util.validation.ControllersResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;
    private final ControllersResponse responses;

    @PatchMapping
    @RequestMapping("update/{puid}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable String puid, @ModelAttribute @Valid UsersUpdateDTO usersUpdateDto) {
        try {
            Users updatedUser = service.updateUser(puid, usersUpdateDto);
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
            UsersDTO gettingUser = service.getUser(puid);
            return new ResponseEntity<>(responses.controllersResponse(gettingUser, null), HttpStatus.OK);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @RequestMapping("list/all")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

}
