package br.com.listtta.backend.util.validation;

import br.com.listtta.backend.exceptions.PhoneNumberNotValidException;
import org.springframework.stereotype.Component;

/**
 * A service for validating and formatting Brazilian phone numbers.
 */
@Component
public class PhoneNumberFormatter {

    /**
     * Formats a given phone number string into the standard Brazilian format.
     *
     * @param phoneNumber the phone number string to be formatted.
     * @return the formatted phone number.
     * @throws PhoneNumberNotValidException if the phone number is not valid.
     */
    public String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            throw new PhoneNumberNotValidException("O número de telefone é inválido");
        }

        String validatedNumber = validatePhoneNumber(phoneNumber);

        //Formatando número
        String stateDDD = validatedNumber.substring(0, 2);
        String leftNumbers = validatedNumber.substring(2, 7);
        String rightNumbers = validatedNumber.substring(7);

        StringBuilder builder = new StringBuilder();
        builder.append("(").append(stateDDD).append(")");
        builder.append(" ").append(leftNumbers).append("-").append(rightNumbers);

        return builder.toString();
    }

    /**
     * Validates a given phone number string by removing non-numeric characters
     * and checking its length.
     *
     * @param phoneNumber the phone number string to be validated.
     * @return the validated phone number string containing only digits.
     * @throws PhoneNumberNotValidException if the phone number is not valid.
     */
    private String validatePhoneNumber(String phoneNumber) {
        String verifyString = phoneNumber.replaceAll("\\D", "");
        if (verifyString.length() != 11) {
            throw new PhoneNumberNotValidException("O número de telefone é inválido");
        }
        return verifyString;
    }
}
