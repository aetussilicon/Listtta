package br.com.listtta.backend.exceptions.users;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("Usuário não encontrado no sistema.");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
