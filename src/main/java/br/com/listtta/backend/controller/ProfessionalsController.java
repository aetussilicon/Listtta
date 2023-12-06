package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.Professionals;
import br.com.listtta.backend.model.dto.ProfessionalsDto;
import br.com.listtta.backend.model.dto.ProfessionalsSignUpDto;
import br.com.listtta.backend.service.ProfessionalsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/professionals")
@RequiredArgsConstructor
public class ProfessionalsController {

    private final ProfessionalsService professionalsService;

    @PostMapping("/signup")
    public ResponseEntity<Professionals> createNewProfessional(@RequestBody @Valid ProfessionalsSignUpDto professionalsSignUpDto) {
        return new ResponseEntity<>(professionalsService.createNewProfessional(professionalsSignUpDto), HttpStatus.CREATED);
    }

    @GetMapping("/list/{professionalId}")
    public ResponseEntity<ProfessionalsDto> getProfessional(@PathVariable UUID professionalId){
        return new ResponseEntity<>(professionalsService.listOneProfessional(professionalId), HttpStatus.OK);
    }
}
