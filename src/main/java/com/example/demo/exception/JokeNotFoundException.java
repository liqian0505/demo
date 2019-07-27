
package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "JokeRequest not found")
public class JokeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public JokeNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public String getMessage() {
        return super.getMessage();
    }
}