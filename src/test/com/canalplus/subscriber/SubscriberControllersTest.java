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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SubscriberControllersTest {
    @Mock
    private SubscriberRepository subscriberRepository;

    @Mock
    private SubscriberMapper subscriberMapper;

    @InjectMocks
    private SubscriberServiceImpl subscriberService;

    @Test
    public void testCreateSubscriber() {
        // Créer un abonné ayant un ID
        SubscriberDto subscriberWithId = new SubscriberDto();
        subscriberWithId.setFname("name");
        subscriberWithId.setLname("lame");
        subscriberWithId.setId(1L);
        // Vérifier qu'une exception est levée lorsque on veut créer cet abonnée (Id ne doit pas etre renseigné)
        assertThrows(IdShouldNotBeProvidedException.class, () -> subscriberService.createSubscriber(subscriberWithId));

        // Créer un abonné n'ayant pas tout les params
        SubscriberDto subscriberWithoutAllParams = new SubscriberDto();
        subscriberWithoutAllParams.setFname("name");
        subscriberWithoutAllParams.setLname("lame");
        // Vérifier qu'une exception est levée lorsque on veut créer cet abonnée (Id ne doit pas etre renseigné)
        assertThrows(AllSubscriberParmatersSHouldBeProvidedException.class, () -> subscriberService.createSubscriber(subscriberWithoutAllParams));

        // Créer un abonné ayant tout les params sauf l'ID (doit etre crée)
        Subscriber actual = new Subscriber();
        SubscriberDto expected = new SubscriberDto();
        actual.setFname("name");
        actual.setLname("lame");
        actual.setMail("mail");
        actual.setPhone("0123456789");
        expected.setFname("name");
        expected.setLname("lame");
        expected.setMail("mail");
        expected.setPhone("0123456789");
        Mockito.when(subscriberRepository.save(Mockito.any(Subscriber.class))).thenReturn(actual);
        Mockito.when(subscriberMapper.toSubscriberDto(Mockito.any(Subscriber.class))).thenReturn(expected);
        SubscriberDto expected2 = subscriberService.createSubscriber(expected);
        actual.setId(1L);
        // Un nouveau abonnée est actif
        assertTrue(expected.isActive());
        //
        assertThat(actual, samePropertyValuesAs(expected2));
        //assertEquals(5, aspirateur.getPositionX());// message
    }


}
