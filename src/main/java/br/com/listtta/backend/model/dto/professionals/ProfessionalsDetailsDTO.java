package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.abstracts.UsersDTOAbstract;
import br.com.listtta.backend.model.enums.ProfessionalsType;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@MappedSuperclass
public class ProfessionalsDetailsDTO extends UsersDTOAbstract {
    private ProfessionalsType type;
    private String instagramUrl;
    private Set<Long> skills;

}
