package br.com.listtta.backend.service;

import br.com.listtta.backend.model.dto.address.NewUserAddressDTO;
import br.com.listtta.backend.model.dto.users.UsersSignupDTO;
import br.com.listtta.backend.model.enums.UserRoles;
import br.com.listtta.backend.util.infra.security.GoogleOauthTokenService;
import br.com.listtta.backend.util.validation.RandomPasswordGenerator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final GoogleOauthTokenService googleService;
    private final UsersService usersService;
    private final EmailService emailService;

    private final RandomPasswordGenerator passwordGenerator;

    public void registerUserWithGoogle(String code) {
        String accessTokenResponse = googleService.getOauthAccessTokenGoogle(code);
        JsonObject accessTokenJson = new Gson().fromJson(accessTokenResponse, JsonObject.class);
        String idToken = accessTokenJson.get("id_token").getAsString();

        JsonObject profile = googleService.getProfileDetailsGoogle(idToken);
        UsersSignupDTO newGoogleUserDTO = new UsersSignupDTO();
        NewUserAddressDTO newGoogleUserAddressDTO = new NewUserAddressDTO();
        String newGoogleUserPassword = passwordGenerator.generatePassword();

        newGoogleUserAddressDTO.setState("SP");
        newGoogleUserAddressDTO.setCity("São Paulo");

        newGoogleUserDTO.setEmail(profile.get("email").getAsString());
        newGoogleUserDTO.setPassword(newGoogleUserPassword);
        newGoogleUserDTO.setRole(UserRoles.USER);
        newGoogleUserDTO.setAddressDto(newGoogleUserAddressDTO);

        usersService.createNewUser(newGoogleUserDTO);

        emailService.sendNewOauthUserPassword(newGoogleUserDTO.getEmail(), newGoogleUserPassword);
    }
}
