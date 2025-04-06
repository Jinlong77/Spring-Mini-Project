package org.kshrd.gamifiedhabittracker.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
