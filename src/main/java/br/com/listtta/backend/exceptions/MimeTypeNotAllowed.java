package br.com.listtta.backend.exceptions;

public class MimeTypeNotAllowed extends RuntimeException {
    public MimeTypeNotAllowed(String mimeType) {
        super("Formato de arquivo" + (mimeType) + " não suportado. Apenas arquivos png, jpg e jpeg são permitido");
    }
}
