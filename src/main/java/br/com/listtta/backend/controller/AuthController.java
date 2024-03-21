package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.service.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsersService usersService;

    @PostMapping
    @RequestMapping("signup")
    public ResponseEntity<Users> registerUser(@RequestBody @Valid UsersSignupDto usersSignupDto) {
        return new ResponseEntity<>(usersService.createNewUser(usersSignupDto), HttpStatus.CREATED);
    }

}
