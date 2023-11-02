package com.canalplus.subscriber.utils;

import com.canalplus.subscriber.dtos.SubscriberDto;
import com.canalplus.subscriber.models.Subscriber;

public class Utils {

    public static SubscriberDto createSuscriberDto() {
        SubscriberDto given = new SubscriberDto();
        given.setFname("Bilel");
        given.setLname("Maalej");
        given.setMail("bilel.maalej@gmail.com");
        given.setPhone("0123456789");
        given.setActive(true);
        return given;
    }

    public static Subscriber createSubscriber() {
        Subscriber actual = new Subscriber();
        actual.setFname("Bilel");
        actual.setLname("Maalej");
        actual.setMail("bilel.maalej@gmail.com");
        actual.setPhone("0123456789");
        actual.setActive(true);
        actual.setId(1L);
        return actual;
    }
}
