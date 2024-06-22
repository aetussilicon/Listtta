package br.com.listtta.backend.util;

import br.com.listtta.backend.exceptions.address.NoUserAddressException;
import br.com.listtta.backend.exceptions.professionals.ProfessionalDetailsNotFoundException;
import br.com.listtta.backend.exceptions.users.UserAlreadyInDatabaseException;
import br.com.listtta.backend.exceptions.users.UserNotFound;
import br.com.listtta.backend.model.entities.Professionals.ProfessionalDetails;
import br.com.listtta.backend.model.entities.address.Address;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.repository.AddressRepository;
import br.com.listtta.backend.repository.ProfessionalsRepository;
import br.com.listtta.backend.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUsersMethods {

    private final UsersRepository usersRepository;
    private final ProfessionalsRepository professionalsRepository;
    private final AddressRepository addressRepository;

    //Procura por usuários em geral
    //Por CPF.
    public Users findUserByTaxNumberAndThrowErro(String taxNumber) {
        return usersRepository.findUsersByTaxNumber(taxNumber).orElseThrow(UserAlreadyInDatabaseException::new);
    }

    public void findUserByEmail(String email) {
        usersRepository.findUsersByEmail(email).orElseThrow(UserAlreadyInDatabaseException::new);
    }

    //Por PUID.
    public Users findUsersByPuid(String puid) {
        return usersRepository.findUserByPuid(puid).orElseThrow(UserNotFound::new);
    }

    //Procura de profissionais.
    //Por usuário.
    public ProfessionalDetails findProfessionalByUser(Users users) {
        return professionalsRepository.findProfessionalByUsers(users).orElseThrow(ProfessionalDetailsNotFoundException::new);
    }

    //Por PUID
    public ProfessionalDetails findProfessionalByPuid (String puid) {
        return professionalsRepository.findProfessionalByPuid(puid).orElseThrow(ProfessionalDetailsNotFoundException::new);
    }

    //Procura de endereços.
    //Por PUID
    public Address findUserAddress(String puid) {
       return  addressRepository.findUserAddressByPuid(puid).orElseThrow(NoUserAddressException::new);
    }
}
