package br.com.listtta.backend.util.validation;

import br.com.listtta.backend.exceptions.UserNotAllowedException;
import br.com.listtta.backend.util.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenValidation {
    private final TokenService tokenService;

    public void compareTokens(String puid, HttpServletRequest userRequest) {
        String requestToken = userRequest.getHeader("Authorization").substring(7);
        String tokenPuid = tokenService.extractPuidFromToken(requestToken);

        if (!puid.equals(tokenPuid)) {
            throw new UserNotAllowedException("Você não tem acesso a essa página");
        }
    }
}