package com.canalplus.subscriber.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IdShouldNotBeProvidedException extends RuntimeException {
    public IdShouldNotBeProvidedException() {
        super("L'ID ne doit pas etre renseigné");
    }
}
