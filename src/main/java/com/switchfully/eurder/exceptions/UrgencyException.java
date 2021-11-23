package com.switchfully.eurder.exceptions;

public class UrgencyException extends RuntimeException {
    private static final String DEFAULT_MESSEAGE = "Your selection for urgency was invalid";

    public UrgencyException() {
        super(DEFAULT_MESSEAGE);
    }
}
