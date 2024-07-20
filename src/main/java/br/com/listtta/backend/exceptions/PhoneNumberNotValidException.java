package br.com.listtta.backend.exceptions;


public class PhoneNumberNotValidException extends RuntimeException{
    public PhoneNumberNotValidException(String message) {
        super(message);
    }
}
