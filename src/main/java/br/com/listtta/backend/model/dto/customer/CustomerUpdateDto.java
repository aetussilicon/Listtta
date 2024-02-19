package br.com.listtta.backend.model.dto.customer;

import br.com.listtta.backend.model.dto.generics.AbstractUserUpdateDto;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerUpdateDto extends AbstractUserUpdateDto {
}
