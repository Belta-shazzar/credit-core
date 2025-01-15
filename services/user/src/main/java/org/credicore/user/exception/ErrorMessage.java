package org.credicore.user.exception;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    private final int statusCode;
    private HttpStatus status;
    private String message;

    public ErrorMessage(int statusCode, HttpStatus status, String message) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
    }
}
