package br.com.listtta.backend.controller;

import br.com.listtta.backend.exceptions.users.UserNotFound;
import br.com.listtta.backend.model.dto.users.UsersDTO;
import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.service.UsersService;
import br.com.listtta.backend.util.validation.ControllersResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService service;
    private final ControllersResponse responses;

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;
    private static final List<String> ALLOWED_MIME_TYPE = Arrays.asList("image/png", "image/jpeg",
            "image/jpg");

    @PatchMapping
    @RequestMapping("update/{puid}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable String puid,
            @ModelAttribute @Valid UsersUpdateDTO usersUpdateDto) {
        Map<String, Object> fileGetResponse = new HashMap<>();

        MultipartFile profilePicture = usersUpdateDto.getProfilePicture();
        if (profilePicture.getSize() > MAX_FILE_SIZE) {
            fileGetResponse.put("Status", "Error");
            fileGetResponse.put("Data", "A imagem deve ter até 5MB.");
            return new ResponseEntity<>(fileGetResponse, HttpStatus.BAD_REQUEST);
        }

        String fileType = profilePicture.getContentType();
        if (!ALLOWED_MIME_TYPE.contains(fileType)) {
            fileGetResponse.put("Status", "Error");
            fileGetResponse.put("Data", "Imagem devem ser dos tipos PNG, JPEG ou JPG");
            return new ResponseEntity<>(fileGetResponse, HttpStatus.BAD_REQUEST);
        }

        try {
            Users updatedUser = service.updateUser(puid, usersUpdateDto);
            return new ResponseEntity<>(responses.controllersResponse(updatedUser, null), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @RequestMapping("list/{puid}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String puid) {
        try {
            UsersDTO gettingUser = service.getUser(puid);
            return new ResponseEntity<>(responses.controllersResponse(gettingUser, null), HttpStatus.OK);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(responses.controllersResponse(null, e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @RequestMapping("list/all")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

}
