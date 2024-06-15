package br.com.listtta.backend.controller;

import br.com.listtta.backend.exceptions.users.UserNotFound;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsDTO;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsUpdateDTO;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalDetails;
import br.com.listtta.backend.service.ProfessionalsService;
import br.com.listtta.backend.util.validation.ControllersResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("professionals")
@RequiredArgsConstructor
public class ProfessionalsController {

    private final ProfessionalsService professionalsService;
    private final ControllersResponse responses;

    //Update de profissionais
    @PatchMapping
    @RequestMapping("update/{puid}")
    public ResponseEntity<ProfessionalDetails> updateDetails(@PathVariable String puid,@RequestBody @Valid ProfessionalsUpdateDTO professionalsUpdateDto) {
        return new ResponseEntity<>(professionalsService.updateProfessionalDetails(puid, professionalsUpdateDto), HttpStatus.ACCEPTED);
    }

    //Get um profissional
    @GetMapping
    @RequestMapping("list/{puid}")
    public ResponseEntity<Map<String, Object>> getProfessional(@PathVariable String puid) {
        try {
            ProfessionalsDTO gettingProfessional = professionalsService.getProfessional(puid);
            return new ResponseEntity<>(responses.controllersResponse(gettingProfessional, null), HttpStatus.OK);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Listar todos os profissionais
    @GetMapping
    @RequestMapping("list/all")
    public ResponseEntity<List<ProfessionalsDTO>> getAllProfessionals() {
        return new ResponseEntity<>(professionalsService.getAllProfessionalsView(), HttpStatus.OK);
    }

}
