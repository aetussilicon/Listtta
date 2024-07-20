package br.com.listtta.backend.model.dto.users;

import br.com.listtta.backend.model.abstracts.UsersDTOAbstract;
import br.com.listtta.backend.model.dto.address.AddressDTO;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class UsersDTO extends UsersDTOAbstract {
    private AddressDTO address;
}
