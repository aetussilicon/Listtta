package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.users.UsersDto;
import br.com.listtta.backend.model.dto.users.UsersUpdateDto;
import br.com.listtta.backend.model.entities.Users;
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
    @RequestMapping("update/{userTag}")
    public ResponseEntity<Users> updateUser(@PathVariable String userTag, @RequestBody @Valid UsersUpdateDto usersUpdateDto) {
        return new ResponseEntity<>(service.updateUser(userTag, usersUpdateDto), HttpStatus.ACCEPTED);
    }

    @GetMapping
    @RequestMapping("list/{userTag}")
    public ResponseEntity<UsersDto> getUser(@PathVariable String userTag) {
        return new ResponseEntity<>(service.getUser(userTag), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("list/all")
    public ResponseEntity<List<UsersDto>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

}
