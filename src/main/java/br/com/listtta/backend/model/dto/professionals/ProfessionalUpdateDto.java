package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.dto.generics.AbstractUserUpdateDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalUpdateDto extends AbstractUserUpdateDto {

    private String instagramUrl;

}
