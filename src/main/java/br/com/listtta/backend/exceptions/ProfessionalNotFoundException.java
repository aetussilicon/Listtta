package br.com.listtta.backend.exceptions;

public class ProfessionalNotFoundException extends RuntimeException{
    public ProfessionalNotFoundException(String message){
        super(message);
    }
}
