package com.canalplus.subscriber.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class IdShouldBeProvidedException extends RuntimeException {
    public IdShouldBeProvidedException() {
        super("Pour mettre à jour l'identifiant doit etre renseigné");
    }
}