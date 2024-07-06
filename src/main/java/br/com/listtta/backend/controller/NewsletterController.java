package br.com.listtta.backend.controller;

import java.util.Map;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.listtta.backend.model.dto.newsletter.NewsletterDTO;
import br.com.listtta.backend.service.NewsletterService;
import br.com.listtta.backend.util.validation.ControllersResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("newsletter")
@RequiredArgsConstructor
public class NewsletterController {
    private final NewsletterService service;
    private final ControllersResponse responses;

    @PostMapping
    @RequestMapping("new")
    public ResponseEntity<Map<String, Object>> newNewsletterEmail(@RequestBody @Valid NewsletterDTO newsletterDTO) {
        try {
            NewsletterDTO responseDTO = service.registerNewNewsletterEmail(newsletterDTO);
            return new ResponseEntity<>(responses.controllersResponse(responseDTO, null), HttpStatus.valueOf(200));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.valueOf(400));
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.valueOf(500));
        }

    }
}
