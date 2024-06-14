package br.com.listtta.backend.model.dto.filters;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewFilterDTO {

    private Long filterId;
    @NotNull private String filterName;
    @NotNull private String displayName;
}
