package com.canalplus.subscriber;


import com.canalplus.subscriber.dtos.SubscriberDto;
import com.canalplus.subscriber.exceptions.AtLeastOneCriteriaShouldBeShoosenException;
import com.canalplus.subscriber.exceptions.SubscriberNotFoundException;
import com.canalplus.subscriber.mappers.SubscriberMapper;
import com.canalplus.subscriber.models.Subscriber;
import com.canalplus.subscriber.models.SubscriberCriteria;
import com.canalplus.subscriber.repositories.SubscriberRepository;
import com.canalplus.subscriber.services.SubscriberServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.canalplus.subscriber.utils.Utils;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SubscriberSearchServiceTest {
    @Mock
    private SubscriberRepository subscriberRepository;
    @Mock
    private SubscriberMapper subscriberMapper;
    @InjectMocks
    private SubscriberServiceImpl subscriberService;

    @Test
    public void test_subscriber_is_founded() {
        // Given criterias
        SubscriberCriteria subscriberCriteria = new SubscriberCriteria();
        subscriberCriteria.setFname("Bilel");
        subscriberCriteria.setLname("Maalej");
        subscriberCriteria.setMail("bilel.maalej@gmail.com");
        subscriberCriteria.setPhone("0123456789");

        List<Subscriber> subscriberList = new ArrayList<>();
        subscriberList.add(Utils.createSubscriber());

        // Given subscriber
        SubscriberDto expectedSubscriberDto = Utils.createSuscriberDto();

        Mockito.when(subscriberRepository.findByCriteria(Mockito.any(SubscriberCriteria.class))).thenReturn(subscriberList);
        List<SubscriberDto> list = subscriberService.searchSubscriber(subscriberCriteria);
        SubscriberDto actualSubscriberDto = list.stream().filter(sc -> sc.getFname().equals("Bilel")).findFirst().get();

        // Vérifier que les attributs des deux objects sont égeaux
        assertEquals(actualSubscriberDto.getFname(), expectedSubscriberDto.getFname());
        assertEquals(actualSubscriberDto.getLname(), expectedSubscriberDto.getLname());
        assertEquals(actualSubscriberDto.getPhone(), expectedSubscriberDto.getPhone());
        assertEquals(actualSubscriberDto.getMail(), expectedSubscriberDto.getMail());
        assertEquals(actualSubscriberDto.isActive(), expectedSubscriberDto.isActive());

    }

    @Test
    public void test_search_throw_subscriber_not_found_exception() {
        SubscriberCriteria subscriberCriteria = new SubscriberCriteria();
        subscriberCriteria.setFname("fname");
        subscriberCriteria.setLname("lname");
        subscriberCriteria.setMail("mail");
        subscriberCriteria.setPhone("phone");

        List<Subscriber> subscriberList = new ArrayList<>();
        Mockito.when(subscriberRepository.findByCriteria(Mockito.any(SubscriberCriteria.class))).thenReturn(subscriberList);
        // Vérifier qu'une exception est levée lorsque on appelle la recherche avec un abonnée inexistant
        assertThrows(SubscriberNotFoundException.class, () -> subscriberService.searchSubscriber(subscriberCriteria));
    }

    @Test
    public void test_search_throw_aleast_one_criteria_exception() {
        SubscriberCriteria subscriberCriteria = new SubscriberCriteria();

        List<Subscriber> subscriberList = new ArrayList<>();
        Mockito.when(subscriberRepository.findByCriteria(Mockito.any(SubscriberCriteria.class))).thenReturn(subscriberList);
        // Vérifier qu'une exception est levée lorsque on appelle la recherche avec aucun critère
        assertThrows(AtLeastOneCriteriaShouldBeShoosenException.class, () -> subscriberService.searchSubscriber(subscriberCriteria));
    }

}
