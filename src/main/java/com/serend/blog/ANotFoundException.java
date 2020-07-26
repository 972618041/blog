package com.serend.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ANotFoundException extends RuntimeException {
    public ANotFoundException() {
    }

    public ANotFoundException(String message) {
        super(message);
    }

    public ANotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
