package br.com.listtta.backend.exceptions;

public class CouldNotRegisterUserException extends RuntimeException{
    public CouldNotRegisterUserException() {
        super("Não foi possível registrar usuário!");
    }

    public CouldNotRegisterUserException(String message) {
        super(message);
    }
}
