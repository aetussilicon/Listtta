package br.com.listtta.backend.exceptions.files;

public class MimeTypeNotAllowedException extends RuntimeException {
    public MimeTypeNotAllowedException(String mimeType) {
        super("Formato de arquivo" + (mimeType) + " não suportado. Apenas arquivos png, jpg e jpeg são permitido");
    }
}
