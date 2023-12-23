package br.com.grimoire.apirestrpgv02.model.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
