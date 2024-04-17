package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDto;
import br.com.listtta.backend.model.entities.ProfessionalDetails;
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
    @RequestMapping("update/{userTag}")
    public ResponseEntity<ProfessionalDetails> updateDetails(@PathVariable String userTag,@RequestBody @Valid ProfessionalsUpdateDto professionalsUpdateDto) {
        return new ResponseEntity<>(professionalsService.updateProfessionalDetails(userTag, professionalsUpdateDto), HttpStatus.ACCEPTED);
    }

    @GetMapping
    @RequestMapping("list/{userTag}")
    public ResponseEntity<ProfessionalsDto> getProfessional(@PathVariable String userTag) {
        return new ResponseEntity<>(professionalsService.getProfessional(userTag), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("list/all")
    public ResponseEntity<List<ProfessionalsDto>> getAllProfessionals() {
        return new ResponseEntity<>(professionalsService.listAllProfessionals(), HttpStatus.OK);
    }
}
