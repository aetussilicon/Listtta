package br.com.listtta.backend.model.dto;

import br.com.listtta.backend.model.entities.Filters;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ProfessionalsSignupDtoComplement {

    private long detailsId;
    private Users users;
    private ProfessionalsType type;
    private String instagramUrl;
}
