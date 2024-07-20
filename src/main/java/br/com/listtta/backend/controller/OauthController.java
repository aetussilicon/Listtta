package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.address.NewUserAddressDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.service.OauthService;
import br.com.listtta.backend.service.UsersService;
import br.com.listtta.backend.util.infra.security.GoogleOauthTokenService;
import br.com.listtta.backend.util.infra.security.TokenService;
import br.com.listtta.backend.util.validation.ControllersResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("oauth")
@RequiredArgsConstructor
public class OauthController {

    private final GoogleOauthTokenService service;
    private final ControllersResponse response;
    private final UsersService usersService;

    @GetMapping
    @RequestMapping("google/grantcode")
    public ResponseEntity<Void> grantCode(@RequestParam("code") String code) {
        String accessTokenResponse = service.getOauthAccessTokenGoogle(code);
        JsonObject accessTokenJson = new Gson().fromJson(accessTokenResponse, JsonObject.class);
        String idToken = accessTokenJson.get("id_token").getAsString();

        JsonObject profile = service.getProfileDetailsGoogle(idToken);
        UsersSignupDTO newGoogleUserDTO = new UsersSignupDTO();
        NewUserAddressDTO newGoogleUserAddressDTO = new NewUserAddressDTO();

        newGoogleUserDTO.setEmail(profile.get("email").getAsString());
        newGoogleUserDTO.setPassword("123");
        newGoogleUserDTO.setProfilePicture(profile.get("picture").getAsString());
        newGoogleUserDTO.setRole(UserRoles.USER);
        newGoogleUserDTO.setAddressDto(newGoogleUserAddressDTO);

        newGoogleUserAddressDTO.setState("SP");
        newGoogleUserAddressDTO.setCity("São Paulo");

        Users newGoogleUser = usersService.createNewUser(newGoogleUserDTO);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:5173/success")).build();
    }
}
