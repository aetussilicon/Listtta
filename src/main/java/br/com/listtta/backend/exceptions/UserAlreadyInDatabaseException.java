package br.com.listtta.backend.exceptions;

public class UserAlreadyInDatabaseException extends RuntimeException {

    public UserAlreadyInDatabaseException() {
        super("Usuário já cadastrado.");
    }

    public UserAlreadyInDatabaseException(String message) {
        super(message);
    }

}
