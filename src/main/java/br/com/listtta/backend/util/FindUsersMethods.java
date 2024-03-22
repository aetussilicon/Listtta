package br.com.listtta.backend.util;

import br.com.listtta.backend.exceptions.ProfessionalDetailsNotFoundException;
import br.com.listtta.backend.exceptions.UserAlreadyInDatabaseException;
import br.com.listtta.backend.exceptions.UserNotFound;
import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import br.com.listtta.backend.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindUsersMethods {

    private final UsersRepository usersRepository;
    private final ProfessionalsRepository professionalsRepository;


    public void findUserByTaxNumberAndThrowErro(String taxNumber) throws UserAlreadyInDatabaseException {
        Optional<Users> checkingUser = usersRepository.findUserByTaxNumber(taxNumber);

        if (checkingUser.isPresent()) {
            throw new UserAlreadyInDatabaseException();
        }
    }

    public Users findUserByUsername(String username) {
        Optional<Users> checkingUser = usersRepository.findUserByUsername(username);

        if (checkingUser.isPresent()) {
            return checkingUser.get();
        } throw new UserNotFound();
    }

    public ProfessionalDetails findProfessionalByUser(Users users) {
        Optional<ProfessionalDetails> checkingDetails = professionalsRepository.findProfessionalByUsers(users);

        if (checkingDetails.isPresent()) {
            return checkingDetails.get();
        } throw new ProfessionalDetailsNotFoundException();

    }

}
