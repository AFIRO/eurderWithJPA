package com.switchfully.eurder.exceptions;

public class AuthorisationException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "You are not authorized to perform this function";

    public AuthorisationException() {
        super(DEFAULT_MESSAGE);
    }

    public AuthorisationException(String message) {
        super(message);
    }
}
