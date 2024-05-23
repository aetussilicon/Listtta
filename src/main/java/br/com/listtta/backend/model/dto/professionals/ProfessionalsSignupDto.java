package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProfessionalsSignupDto {

    private long detailsId;
    private Users users;
    private String puid;
    @Nullable private ProfessionalsType type;
    private String instagramUrl;
    @Nullable private Set<Long> skills;
}
