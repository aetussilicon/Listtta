package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.professionals.ProfessionalUpdateDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignUpDto;
import br.com.listtta.backend.model.entities.Professionals;
import br.com.listtta.backend.service.ProfessionalsService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PatchMapping("/update/{professionalId}")
    @Transactional
    public ResponseEntity<Professionals> patchProfessional(@PathVariable UUID professionalId, @RequestBody ProfessionalUpdateDto professionalUpdateDto){
        return new ResponseEntity<>(professionalsService.patchProfessional(professionalId, professionalUpdateDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/list/professional/{professionalId}")
    public ResponseEntity<ProfessionalsDto> getProfessional(@PathVariable UUID professionalId){
        return new ResponseEntity<>(professionalsService.listOneProfessional(professionalId), HttpStatus.OK);
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<ProfessionalsDto>> listAllProfessionals() {
        return new ResponseEntity<>(professionalsService.listAllProfessionals(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{professionalId}")
    public ResponseEntity<Professionals> deleteProfessional(@PathVariable UUID professionalId) {
        if (professionalsService.deleteProfessional(professionalId)) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
