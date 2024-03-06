package br.com.listtta.backend.util.validation;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.format.Formatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import org.springframework.stereotype.Component;

@Component
public class CPFValidatorService {

    private String cpfFormatter(String cpfToFormat) {
        Formatter cpfFormatter = new CPFFormatter();
        return cpfFormatter.format(cpfToFormat);
    }

    public String cpfValidation(String cpfToValidate) {
        CPFValidator validator = new CPFValidator();
        try {
            validator.assertValid(cpfToValidate);
            return cpfFormatter(cpfToValidate);
        } catch (InvalidStateException e) {
            throw new InvalidStateException(e.getInvalidMessages());
        }
    }
}
