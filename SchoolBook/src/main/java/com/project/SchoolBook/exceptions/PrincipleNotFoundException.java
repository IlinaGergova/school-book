package com.project.SchoolBook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PrincipleNotFoundException extends RuntimeException {
    public PrincipleNotFoundException(String message) {
        super(message);
    }
}
