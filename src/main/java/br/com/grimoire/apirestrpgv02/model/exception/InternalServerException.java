package br.com.grimoire.apirestrpgv02.model.exception;

public class InternalServerException extends RuntimeException {

    public InternalServerException(String message) {
        super(message);
    }
}
