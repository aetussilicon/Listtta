package br.com.listtta.backend.exceptions;

public class ProfessionalDetailsNotFoundException extends RuntimeException{
    public ProfessionalDetailsNotFoundException() {
        super("Detalhes do profissional não encontrados.");
    }

    public ProfessionalDetailsNotFoundException(String message) {
        super(message);
    }
}
