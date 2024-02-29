package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.dto.generics.AbstractUserSignUpDto;
import br.com.listtta.backend.model.entities.Filters;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProfessionalsSignUpDto extends AbstractUserSignUpDto {

    @NotNull private String instagramUrl;
    @NotNull Set<Filters> professionalSkills;
}
