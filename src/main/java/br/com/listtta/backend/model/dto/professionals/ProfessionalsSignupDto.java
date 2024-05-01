package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalsSignupDto {

    private long detailsId;
    private Users users;
    private String puid;
    @NotNull private ProfessionalsType type;
    @NotNull private String instagramUrl;
}
