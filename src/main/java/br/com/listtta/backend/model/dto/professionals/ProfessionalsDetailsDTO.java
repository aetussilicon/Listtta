package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.entities.filters.Filters;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProfessionalsDetailsDTO {
    private ProfessionalsType type;
    private String instagramUrl;
    private Set<Filters> skills;

}
