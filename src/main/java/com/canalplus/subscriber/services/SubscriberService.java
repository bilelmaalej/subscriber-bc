package com.canalplus.subscriber.services;


import com.canalplus.subscriber.dtos.SubscriberDto;
import com.canalplus.subscriber.models.SubscriberCriteria;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubscriberService {
    SubscriberDto createSubscriber(SubscriberDto subscriberDto);
    List<SubscriberDto> getSubscriberByCriteria(SubscriberCriteria subscriberCriteria);
    void cancelSubscriber(Long id);
    SubscriberDto updateSubscriber(SubscriberDto subscriberDto, Long subscriberId);
    List<SubscriberDto> findAll();
    List<SubscriberDto> searchSubscriber(SubscriberCriteria criteria);

//    UserDetailsService userDetailsService();
}
