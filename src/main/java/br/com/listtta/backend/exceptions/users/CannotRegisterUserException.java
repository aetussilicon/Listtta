package br.com.listtta.backend.exceptions.users;

public class CannotRegisterUserException extends RuntimeException{
    public CannotRegisterUserException() {
        super("Não foi possível registrar usuário!");
    }

    public CannotRegisterUserException(String message) {
        super(message);
    }
}
