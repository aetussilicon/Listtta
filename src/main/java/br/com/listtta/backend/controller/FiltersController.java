package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.generics.CreateNewFilterDto;
import br.com.listtta.backend.model.dto.generics.FiltersDto;
import br.com.listtta.backend.model.entities.Filters;
import br.com.listtta.backend.service.FiltersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("filters")
@RequiredArgsConstructor
public class FiltersController {

    private final FiltersService filtersService;

    @PostMapping("create")
    public ResponseEntity<Filters> createNewFilter(@RequestBody @Valid CreateNewFilterDto createNewFilterDto) {
        return new ResponseEntity<>(filtersService.createNewFilter(createNewFilterDto), HttpStatus.CREATED);
    }

    @GetMapping("list/{filterName}")
    public ResponseEntity<FiltersDto> getOneFilter(@PathVariable String filterName) {
        return new ResponseEntity<>(filtersService.getOneFilter(filterName), HttpStatus.OK);
    }

    @GetMapping("list/all")
    public ResponseEntity<List<FiltersDto>> getAllFilters() {
        return new ResponseEntity<>(filtersService.gerAllFilters(), HttpStatus.OK);
    }

}
