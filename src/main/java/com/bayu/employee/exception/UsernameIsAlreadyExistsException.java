package com.bayu.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameIsAlreadyExistsException extends RuntimeException {

    public UsernameIsAlreadyExistsException() {
    }

    public UsernameIsAlreadyExistsException(String message) {
        super(message);
    }
}
