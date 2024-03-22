package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.users.UsersDto;
import br.com.listtta.backend.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;

    @GetMapping
    @RequestMapping("list/{username}")
    public ResponseEntity<UsersDto> getUser(@PathVariable String username) {
        return new ResponseEntity<>(service.getUser(username), HttpStatus.OK);
    }

}
