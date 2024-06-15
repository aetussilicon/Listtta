package br.com.listtta.backend.controller;

import br.com.listtta.backend.exceptions.users.CannotUpdateUsersFieldsException;
import br.com.listtta.backend.model.dto.filters.CreateNewFilterDTO;
import br.com.listtta.backend.model.dto.filters.FiltersDTO;
import br.com.listtta.backend.model.dto.filters.UpdateFilterDTO;
import br.com.listtta.backend.model.entities.filters.Filters;
import br.com.listtta.backend.service.FiltersService;
import br.com.listtta.backend.util.validation.ControllersResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("filters")
@RequiredArgsConstructor
public class FiltersController {

    private final FiltersService service;
    private final ControllersResponse responses;

    @PostMapping("create")
    public ResponseEntity<Map<String, Object>> createNewFilter(@RequestBody @Valid CreateNewFilterDTO createNewFilterDto) {
        try {
            Filters newFilter = service.createNewFilter(createNewFilterDto);
            return new ResponseEntity<>(responses.controllersResponse(newFilter, null), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PatchMapping("update/{filterId}")
    public ResponseEntity<Map<String, Object>> patchFilter(@PathVariable Long filterId, @RequestBody UpdateFilterDTO updateFilterDto) {
        try {
            Filters updatedFilter = service.patchFilter(filterId, updateFilterDto);
            return new ResponseEntity<>(responses.controllersResponse(updatedFilter, null), HttpStatus.OK);
        } catch (CannotUpdateUsersFieldsException e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("list/{filterName}")
    public ResponseEntity<Map<String, Object>> getOneFilter(@PathVariable String filterName) {
        try {
            FiltersDTO gettingFilter = service.getOneFilter(filterName);
            return new ResponseEntity<>(responses.controllersResponse(gettingFilter, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("list/all")
    public ResponseEntity<List<FiltersDTO>> getAllFilters() {
        return new ResponseEntity<>(service.gerAllFilters(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{filterId}")
    public ResponseEntity<Map<String, Object>> deleteFilter(@PathVariable Long filterId) {
        try {
            String deleteString = service.deleteFilter(filterId);
            return new ResponseEntity<>(responses.controllersResponse(deleteString, null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
