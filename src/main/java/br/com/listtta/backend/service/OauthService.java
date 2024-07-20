package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.address.NewUserAddressDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.util.infra.security.GoogleOauthTokenService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final GoogleOauthTokenService googleService;
    private final UsersService usersService;

    public void registerUserWithGoogle(String code) {
        String accessTokenResponse = googleService.getOauthAccessTokenGoogle(code);
        JsonObject accessTokenJson = new Gson().fromJson(accessTokenResponse, JsonObject.class);
        String idToken = accessTokenJson.get("id_token").getAsString();

        JsonObject profile = googleService.getProfileDetailsGoogle(idToken);
        UsersSignupDTO newGoogleUserDTO = new UsersSignupDTO();
        NewUserAddressDTO newGoogleUserAddressDTO = new NewUserAddressDTO();

        newGoogleUserAddressDTO.setState("SP");
        newGoogleUserAddressDTO.setCity("São Paulo");

        newGoogleUserDTO.setEmail(profile.get("email").getAsString());
        newGoogleUserDTO.setPassword("123");
        newGoogleUserDTO.setRole(UserRoles.USER);
        newGoogleUserDTO.setAddressDto(newGoogleUserAddressDTO);

        usersService.createNewUser(newGoogleUserDTO);
    }
}
