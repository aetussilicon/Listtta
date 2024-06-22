package br.com.listtta.backend.exceptions.address;

public class NoUserAddressException extends RuntimeException{
    public NoUserAddressException() {
        super("Não há nenhum endereço salvo para o usuário");
    }
}
