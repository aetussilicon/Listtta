package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.generics.CreateNewFilterDto;
import br.com.listtta.backend.model.entities.Filters;
import br.com.listtta.backend.service.FiltersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("filters")
@RequiredArgsConstructor
public class FiltersController {

    private final FiltersService filtersService;

    @PostMapping("create")
    public ResponseEntity<Filters> createNewFilter(@RequestBody @Valid CreateNewFilterDto createNewFilterDto) {
        return new ResponseEntity<>(filtersService.createNewFilter(createNewFilterDto), HttpStatus.CREATED);
    }

}
