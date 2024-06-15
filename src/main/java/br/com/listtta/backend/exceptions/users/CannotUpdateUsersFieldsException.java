package br.com.listtta.backend.exceptions.users;

public class CannotUpdateUsersFieldsException extends RuntimeException{
    public CannotUpdateUsersFieldsException(String message){
        super(message);
    }
}
