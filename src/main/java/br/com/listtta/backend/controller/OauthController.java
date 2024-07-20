package br.com.listtta.backend.controller;

import br.com.listtta.backend.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("oauth")
@RequiredArgsConstructor
public class OauthController {
    private final OauthService service;

    @GetMapping
    @RequestMapping("google/grantcode")
    public ResponseEntity<Void> grantCode(@RequestParam("code") String code) {
        service.registerUserWithGoogle(code);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:5173/oauth/success")).build();
    }
}
