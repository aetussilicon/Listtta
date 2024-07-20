package br.com.listtta.backend.util.validation;

import br.com.listtta.backend.exceptions.PhoneNumberNotValidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PhoneNumberValidationService.
 */
class PhoneNumberValidationServiceTest {
    private final PhoneNumberValidationService phoneNumberValidationService = new PhoneNumberValidationService();

    @Test
    void testFormatPhoneNumber_ValidNumber() {
        String input = "11973935384";
        String expected = "(11) 97393-5384";
        String actual = phoneNumberValidationService.formatPhoneNumber(input);
        assertEquals(expected, actual);
    }

    @Test
    void testFormatPhoneNumber_WithNoNumericCharacters() {
        String input = "(+11) 98765-4321";
        String expected = "(11) 98765-4321";
        String actual = phoneNumberValidationService.formatPhoneNumber(input);
        assertEquals(expected, actual);
    }

    @Test
    void testFormatPhoneNumber_EmptyString() {
        String input = "";

        try {
            phoneNumberValidationService.formatPhoneNumber(input);
            fail("Expected PhoneNumberNotValidException to be thrown");
        } catch (PhoneNumberNotValidException e) {
            assertEquals("O número de telefone é inválido", e.getMessage());
        }
    }

    @Test
    void testFormatPhoneNumber_NullInput() {
        String input = null;

        try {
            phoneNumberValidationService.formatPhoneNumber(input);
            fail("Expected PhoneNumberNotValidException to be thrown");
        } catch (PhoneNumberNotValidException e) {
            assertEquals("O número de telefone é inválido", e.getMessage());
        } catch (NullPointerException e) {
            fail("Unexpected NullPointerException was thrown");
        }
    }
}