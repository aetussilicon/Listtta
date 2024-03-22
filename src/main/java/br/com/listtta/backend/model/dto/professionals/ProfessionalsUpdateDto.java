package br.com.listtta.backend.model.dto.professionals;

import br.com.listtta.backend.model.enums.ProfessionalsType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessionalsUpdateDto {

    private ProfessionalsType type;
    private String instagramUrl;

}
