package br.com.listtta.backend.model.dto.customer;

import br.com.listtta.backend.model.dto.generics.AbstractUserSignUpDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerSignUpDto extends AbstractUserSignUpDto {

}
