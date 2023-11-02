package com.canalplus.subscriber.mappers;


import com.canalplus.subscriber.dtos.SubscriberDto;
import com.canalplus.subscriber.models.Subscriber;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriberMapper {
    SubscriberDto toSubscriberDto(Subscriber subscriber);
    List<SubscriberDto> toSubscriberDtoList(List<Subscriber> subscriberList);
    Subscriber toSubscriber(SubscriberDto subscriberDto);
}
