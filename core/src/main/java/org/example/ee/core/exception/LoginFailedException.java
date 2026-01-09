package org.example.ee.core.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class LoginFailedException extends RuntimeException{
    public LoginFailedException(String message) {
        super(message);
    }
}

