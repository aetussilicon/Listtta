package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.filters.CreateNewFilterDTO;
import br.com.listtta.backend.model.dto.filters.FiltersDTO;
import br.com.listtta.backend.model.dto.filters.UpdateFilterDTO;
import br.com.listtta.backend.model.entities.filters.Filters;
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
    public ResponseEntity<Filters> createNewFilter(@RequestBody @Valid CreateNewFilterDTO createNewFilterDto) {
        return new ResponseEntity<>(filtersService.createNewFilter(createNewFilterDto), HttpStatus.CREATED);
    }

    @PatchMapping("update/{filterId}")
    public ResponseEntity<Filters> patchFilter(@PathVariable Long filterId, @RequestBody UpdateFilterDTO updateFilterDto) {
        return new ResponseEntity<>(filtersService.patchFilter(filterId, updateFilterDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("list/{filterName}")
    public ResponseEntity<FiltersDTO> getOneFilter(@PathVariable String filterName) {
        return new ResponseEntity<>(filtersService.getOneFilter(filterName), HttpStatus.OK);
    }

    @GetMapping("list/all")
    public ResponseEntity<List<FiltersDTO>> getAllFilters() {
        return new ResponseEntity<>(filtersService.gerAllFilters(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{filterId}")
    public ResponseEntity<String> deleteFilter(@PathVariable Long filterId) {
        return new ResponseEntity<>(filtersService.deleteFilter(filterId), HttpStatus.ACCEPTED);
    }

}
