package br.com.listtta.backend.util.validation;

import br.com.listtta.backend.model.dto.users.UsersUpdateDTO;
import br.com.listtta.backend.model.entities.users.Users;
import br.com.listtta.backend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateFieldsService {
    // Formatter para números de telefone
    private final PhoneNumberFormatter phoneNumberFormatter;

    // Serviço para manipulação de endereços
    private final AddressService addressService;

    /**
     * Atualiza o número de telefone do usuário com base nas informações fornecidas.
     *
     * @param updateDTO DTO contendo as novas informações do usuário.
     * @param userToUpdate Entidade do usuário a ser atualizado.
     */
    public void updateUserPhoneNumber(UsersUpdateDTO updateDTO, Users userToUpdate) {
        Optional.ofNullable(updateDTO.getPhoneNumber())
                .map(phoneNumberFormatter::formatPhoneNumber)
                .ifPresent(userToUpdate::setPhoneNumber);
    }

    /**
     * Atualiza o contato de WhatsApp do usuário com base nas informações fornecidas.
     *
     * @param updateDTO DTO contendo as novas informações do usuário.
     * @param userToUpdate Entidade do usuário a ser atualizado.
     */
    public void updateUserWhatsappContact(UsersUpdateDTO updateDTO, Users userToUpdate) {
        Optional.ofNullable(updateDTO.getWhatsappContact())
                .map(phoneNumberFormatter::formatPhoneNumber)
                .ifPresent(userToUpdate::setWhatsappContact);
    }

    /**
     * Atualiza os campos de endereço do usuário com base nas informações fornecidas.
     *
     * @param puid Identificador do usuário cuja informação de endereço será atualizada.
     * @param updateDTO DTO contendo as novas informações do usuário.
     */
    public void updateUserAddressFields(String puid, UsersUpdateDTO updateDTO) {
        if (updateDTO.getAddress() != null) {
            addressService.updateUserAddress(puid, updateDTO);
        }
    }
}
