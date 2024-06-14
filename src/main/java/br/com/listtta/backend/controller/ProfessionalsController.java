package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalDetails;
import br.com.listtta.backend.service.ProfessionalsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("professionals")
@RequiredArgsConstructor
public class ProfessionalsController {

    private final ProfessionalsService professionalsService;

    @PatchMapping
    @RequestMapping("update/{puid}")
    public ResponseEntity<ProfessionalDetails> updateDetails(@PathVariable String puid,@RequestBody @Valid ProfessionalsUpdateDTO professionalsUpdateDto) {
        return new ResponseEntity<>(professionalsService.updateProfessionalDetails(puid, professionalsUpdateDto), HttpStatus.ACCEPTED);
    }

    @GetMapping
    @RequestMapping("list/{puid}")
    public ResponseEntity<ProfessionalsDTO> getProfessional(@PathVariable String puid) {
        return new ResponseEntity<>(professionalsService.getProfessional(puid), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("list/all")
    public ResponseEntity<List<ProfessionalsDTO>> getAllProfessionals() {
        return new ResponseEntity<>(professionalsService.getAllProfessionalsView(), HttpStatus.OK);
    }

}
