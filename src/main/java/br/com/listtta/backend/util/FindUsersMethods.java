package br.com.listtta.backend.util;

import br.com.listtta.backend.exceptions.ProfessionalDetailsNotFoundException;
import br.com.listtta.backend.exceptions.UserAlreadyInDatabaseException;
import br.com.listtta.backend.exceptions.UserNotFound;
import br.com.listtta.backend.model.entities.Address;
import br.com.listtta.backend.model.entities.ProfessionalDetails;
import br.com.listtta.backend.model.entities.Users;
import br.com.listtta.backend.repository.AddressRepository;
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
    private final AddressRepository addressRepository;

    private Optional<Users> checkingUser;

    //Procura por usuários em geral
    //Por CPF.
    public void findUserByTaxNumberAndThrowErro(String taxNumber) throws UserAlreadyInDatabaseException {
        checkingUser = usersRepository.findUsersByTaxNumber(taxNumber);

        if (checkingUser.isPresent()) {
            throw new UserAlreadyInDatabaseException();
        }
    }

    public void findUserByEmail(String email) {
        checkingUser = usersRepository.findUsersByEmail(email);

        if (checkingUser.isPresent()) {
            throw new UserAlreadyInDatabaseException();
        }
    }

    //Por PUID.
    public Users findUsersByPuid(String puid) {
        checkingUser = usersRepository.findUserByPuid(puid);

        if (checkingUser.isPresent()) {
            return checkingUser.get();
        } throw new UserNotFound();
    }

    //Procura de profissionais.
    //Por usuário.
    public ProfessionalDetails findProfessionalByUser(Users users) {
        Optional<ProfessionalDetails> checkingDetails = professionalsRepository.findProfessionalByUsers(users);

        if (checkingDetails.isPresent()) {
            return checkingDetails.get();
        } throw new ProfessionalDetailsNotFoundException();

    }

    //Procura de endereços.
    //Por PUID
    public Address findUserAddress(String puid) {
        Optional<Address> checkAddressInDatabase = addressRepository.findUserAddressByPuid(puid);
        if (checkAddressInDatabase.isPresent()) {
            return checkAddressInDatabase.get();
        } return null;
    }

    //

}
