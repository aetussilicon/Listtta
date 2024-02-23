package br.com.listtta.backend.model.dto.generics;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewFilterDto {

    private Long filterId;
    @NotNull private String filterName;
    @NotNull private String displayName;
}
