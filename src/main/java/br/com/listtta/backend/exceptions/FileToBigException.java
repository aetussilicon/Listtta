package br.com.listtta.backend.exceptions;

public class FileToBigException extends RuntimeException {
    public FileToBigException() {
        super("Arquivo muito grande. Apenas imagens de até 5MB são permitidas");
    }
}
