package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.users.UsersDTO;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;

    @PatchMapping
    @RequestMapping("update/{puid}")
    public ResponseEntity<Users> updateUser(@PathVariable String puid, @RequestBody @Valid UsersUpdateDTO usersUpdateDto) {
        return new ResponseEntity<>(service.updateUser(puid, usersUpdateDto), HttpStatus.ACCEPTED);
    }

    @GetMapping
    @RequestMapping("list/{puid}")
    public ResponseEntity<UsersDTO> getUser(@PathVariable String puid) {
        return new ResponseEntity<>(service.getUser(puid), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("list/all")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

}
