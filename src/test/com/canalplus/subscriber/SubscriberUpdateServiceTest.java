package com.canalplus.subscriber;


import com.canalplus.subscriber.dtos.SubscriberDto;
import com.canalplus.subscriber.exceptions.AllSubscriberParmatersSHouldBeProvidedException;
import com.canalplus.subscriber.exceptions.IdShouldNotBeProvidedException;
import com.canalplus.subscriber.mappers.SubscriberMapper;
import com.canalplus.subscriber.models.Subscriber;
import com.canalplus.subscriber.repositories.SubscriberRepository;
import com.canalplus.subscriber.services.SubscriberServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubscriberUpdateServiceTest {
    @Mock
    private SubscriberRepository subscriberRepository;
    @Mock
    private SubscriberMapper subscriberMapper;
    @InjectMocks
    private SubscriberServiceImpl subscriberService;

    @Test
    public void test_correct_ubscriber() {
        // Créer un abonné ayant tout les params sauf l'ID (doit etre crée)
        Subscriber actual = new Subscriber();
        actual.setFname("fname");
        actual.setLname("lame");
        actual.setMail("mail");
        actual.setPhone("0123456789");
        actual.setActive(true);
        actual.setId(1L);

        SubscriberDto expected = new SubscriberDto();
        expected.setFname("fname");
        expected.setLname("lame");
        expected.setMail("mail");
        expected.setPhone("0123456789");
        expected.setActive(true);

        Mockito.when(subscriberRepository.save(Mockito.any(Subscriber.class))).thenReturn(actual);
        Mockito.when(subscriberMapper.toSubscriberDto(Mockito.any(Subscriber.class))).thenReturn(expected);

        SubscriberDto expected2 = subscriberService.createSubscriber(expected);

        // Un nouveau abonnée est actif
        assertTrue(expected.isActive());
        expected.setId(1L);
        assertEquals(expected.getId(), expected2.getId());
        assertEquals(expected.getFname(), expected2.getFname());
        assertEquals(expected.getLname(), expected2.getLname());
        assertEquals(expected.getPhone(), expected2.getPhone());
        assertEquals(expected.getMail(), expected2.getMail());
        assertEquals(expected.isActive(), expected2.isActive());
    }

    @Test
    public void test_params_should_throw_exception() {
        // Créer un abonné n'ayant pas tout les params
        SubscriberDto subscriberWithoutAllParams = new SubscriberDto();
        subscriberWithoutAllParams.setFname("name");
        subscriberWithoutAllParams.setLname("lame");
        // Vérifier qu'une exception est levée lorsque on veut créer cet abonnée (On doit fournir tout les parametres)
        assertThrows(AllSubscriberParmatersSHouldBeProvidedException.class, () -> subscriberService.createSubscriber(subscriberWithoutAllParams));
    }

    @Test
    public void test_id_should_throw_exception() {
        // Créer un abonné ayant un ID
        SubscriberDto subscriberWithId = new SubscriberDto();
        subscriberWithId.setFname("name");
        subscriberWithId.setLname("lame");
        subscriberWithId.setId(1L);
        // Vérifier qu'une exception est levée lorsque on veut créer cet abonnée (Id ne doit pas etre renseigné)
        assertThrows(IdShouldNotBeProvidedException.class, () -> subscriberService.createSubscriber(subscriberWithId));
    }


}
