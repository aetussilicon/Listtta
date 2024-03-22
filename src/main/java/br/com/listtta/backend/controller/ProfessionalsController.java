package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.service.ProfessionalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("professionals")
@RequiredArgsConstructor
public class ProfessionalsController {

    private final ProfessionalsService professionalsService;

    @GetMapping
    @RequestMapping("list/{username}")
    public ResponseEntity<ProfessionalsDto> getProfessional(@PathVariable String username) {
        return new ResponseEntity<>(professionalsService.getProfessional(username), HttpStatus.OK);
    }
}
