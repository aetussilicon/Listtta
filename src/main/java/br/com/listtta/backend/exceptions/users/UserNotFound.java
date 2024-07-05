package br.com.listtta.backend.exceptions.users;

public class UserNotFound extends RuntimeException{

    public UserNotFound() {
        super("Usuário não encontrado no sistema.");
    }

    public UserNotFound(String message){
        super(message);
    }
}
