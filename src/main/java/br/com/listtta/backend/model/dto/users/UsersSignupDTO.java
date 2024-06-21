package br.com.listtta.backend.model.dto.users;

import br.com.listtta.backend.model.abstracts.UsersDTOAbstract;
import br.com.listtta.backend.model.dto.professionals.ProfessionalsSignupDTO;
import br.com.listtta.backend.model.entities.address.Address;
import br.com.listtta.backend.model.enums.UserGender;
import br.com.listtta.backend.model.enums.UserRoles;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class UsersSignupDTO extends UsersDTOAbstract {

    private UUID userId;

    @Override
    @NotNull
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
      super.setEmail(email);
    }

    @Override
    @NotNull
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    @NotNull
    public UserGender getGender() {
        return super.getGender();
    }

    @Override
    public void setGender(UserGender gender) {
        super.setGender(gender);
    }

    @Override
    @NotNull
    public UserRoles getRole() {
        return super.getRole();
    }

    @Override
    public void setRole(UserRoles role) {
        super.setRole(role);
    }

    @NotNull private Address address;
    private ProfessionalsSignupDTO professionalsDto;
}
