package com.canalplus.subscriber.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AtLeastOneCriteriaShouldBeShoosenException extends RuntimeException {
    public AtLeastOneCriteriaShouldBeShoosenException() {
        super("Au moin un critère doit etre renseigné");
    }
}