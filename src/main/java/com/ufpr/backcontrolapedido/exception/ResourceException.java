package com.ufpr.backcontrolapedido.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceException extends RuntimeException {

    public ResourceException(String message) {
        super(message);
    }
}