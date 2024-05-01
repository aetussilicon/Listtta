package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.ProfessionalsRequestedParamsDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDto;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDto;
import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import br.com.listtta.backend.model.enums.UserGender;
import br.com.listtta.backend.service.ProfessionalsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
    public ResponseEntity<ProfessionalDetails> updateDetails(@PathVariable String puid,@RequestBody @Valid ProfessionalsUpdateDto professionalsUpdateDto) {
        return new ResponseEntity<>(professionalsService.updateProfessionalDetails(puid, professionalsUpdateDto), HttpStatus.ACCEPTED);
    }

    @GetMapping
    @RequestMapping("list/{puid}")
    public ResponseEntity<ProfessionalsDto> getProfessional(@PathVariable String puid) {
        return new ResponseEntity<>(professionalsService.getProfessional(puid), HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("list/all")
    public ResponseEntity<List<ProfessionalsDto>> getAllProfessionals() {
        return new ResponseEntity<>(professionalsService.listAllProfessionals(), HttpStatus.OK);
    }
}
