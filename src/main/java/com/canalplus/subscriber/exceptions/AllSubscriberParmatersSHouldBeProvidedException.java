package com.canalplus.subscriber.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AllSubscriberParmatersSHouldBeProvidedException extends RuntimeException {
    public AllSubscriberParmatersSHouldBeProvidedException() {
        super("Toutes les données personnelles doivent être fournies");
    }
}