package br.com.listtta.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsViewDTO;
import br.com.listtta.backend.service.ProfessionalsService;
import br.com.listtta.backend.util.validation.ControllersResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("professionals")
@RequiredArgsConstructor
public class ProfessionalsController {

    private final ProfessionalsService service;
    private final ControllersResponse responses;

    @GetMapping
    @RequestMapping("list/all")
    public ResponseEntity<Map<String, Object>> listAllProfessionals() {
        try {
            List<ProfessionalsViewDTO> professionalsList = service.listAllProfessionals();
            return new ResponseEntity<>(responses.controllersResponse(professionalsList, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.BAD_REQUEST);
        }

    }
}
