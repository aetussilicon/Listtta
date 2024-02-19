package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.dto.generics.AbstractUserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProfessionalsDto extends AbstractUserDto {

    private String instagramUrl;

}
