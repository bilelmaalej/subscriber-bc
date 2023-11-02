package com.canalplus.subscriber.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SubscriberAlreadyExistsException extends RuntimeException {
    public SubscriberAlreadyExistsException() {
        super("L'abonné existe déja");
    }
}
