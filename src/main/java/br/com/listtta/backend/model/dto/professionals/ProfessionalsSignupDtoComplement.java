package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalsSignupDtoComplement {

    private long detailsId;
    private Users users;
    private ProfessionalsType type;
    private String instagramUrl;
}
