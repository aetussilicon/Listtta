package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.dto.generics.AbstractUserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalsDto extends AbstractUserDto {

    private String instagramUrl;

}
