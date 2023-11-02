package com.canalplus.subscriber.repositories;

import com.canalplus.subscriber.models.Subscriber;
import com.canalplus.subscriber.models.SubscriberCriteria;

import java.util.List;

public interface SubscriberRepositoryCustom {
    List<Subscriber> findByCriteria(SubscriberCriteria subscriberCriteria);
}
