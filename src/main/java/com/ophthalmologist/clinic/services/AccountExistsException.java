package com.ophthalmologist.clinic.services;

public class AccountExistsException extends RuntimeException {
    private final String username;

    public AccountExistsException(String username) {
        this.username = username;
    }

    public AccountExistsException(String message, String username) {
        super(message);
        this.username = username;
    }

    public AccountExistsException(String message, Throwable cause, String username) {
        super(message, cause);
        this.username = username;
    }


    public String getUsername() {
        return username;
    }
}
