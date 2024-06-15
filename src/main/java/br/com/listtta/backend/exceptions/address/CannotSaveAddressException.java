package br.com.listtta.backend.exceptions.address;

public class CannotSaveAddressException extends RuntimeException  {
    public CannotSaveAddressException() {
        super("Não foi possível salvar o endereço do usuário");
    }
}
