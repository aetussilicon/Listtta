package br.com.listtta.backend.model.dto.professionals;

import java.util.Set;

import br.com.listtta.backend.model.enums.ProfessionalsType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailsDTO {
    private ProfessionalsType type;
    private String instagramUrl;
    private Set<Long> skills;
}
