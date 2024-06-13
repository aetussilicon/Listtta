package br.com.listtta.backend.controller;

import br.com.listtta.backend.exceptions.CouldNotRegisterUserException;
import br.com.listtta.backend.exceptions.UserAlreadyInDatabaseException;
import br.com.listtta.backend.model.dto.authentication.LoginDto;
import br.com.listtta.backend.model.dto.authentication.LoginResponseDto;
import br.com.listtta.backend.model.dto.users.UsersSignupDto;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.service.UsersService;
import br.com.listtta.backend.util.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    @RequestMapping("login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto) {
       var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
       var auth = this.authenticationManager.authenticate(usernamePassword);

       var token = tokenService.generateToken((Users) auth.getPrincipal());

       return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping
    @RequestMapping("signup")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody @Valid UsersSignupDto usersSignupDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Users newUser = usersService.createNewUser(usersSignupDto);
            response.put("Status", "Success");
            response.put("User", newUser.getPuid());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UserAlreadyInDatabaseException e) {
            response.put("Status", "Error");
            response.put("Data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }


    }
}
