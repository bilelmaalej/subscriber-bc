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

import com.canalplus.subscriber.utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubscriberCreateServiceTest {
    @Mock
    private SubscriberRepository subscriberRepository;
    @Mock
    private SubscriberMapper subscriberMapper;
    @InjectMocks
    private SubscriberServiceImpl subscriberService;

    @Test
    public void test_correct_ubscriber() {
        // Créer un abonné ayant tout les params sauf l'ID (doit etre crée)
        Mockito.when(subscriberRepository.save(Mockito.any(Subscriber.class))).thenReturn(Utils.createSubscriber());
        SubscriberDto expectedDto = Utils.createSuscriberDto();
        Mockito.when(subscriberMapper.toSubscriberDto(Mockito.any(Subscriber.class))).thenReturn(expectedDto);

        SubscriberDto subscriberDtoExpected = subscriberService.createSubscriber(Utils.createSuscriberDto());

        // Un nouveau abonnée est actif
        assertTrue(expectedDto.isActive());
        expectedDto.setId(1L);

        assertEquals(expectedDto.getFname(), subscriberDtoExpected.getFname());
        assertEquals(expectedDto.getLname(), subscriberDtoExpected.getLname());
        assertEquals(expectedDto.getPhone(), subscriberDtoExpected.getPhone());
        assertEquals(expectedDto.getMail(), subscriberDtoExpected.getMail());
        assertEquals(expectedDto.isActive(), subscriberDtoExpected.isActive());
    }

    @Test
    public void test_params_should_throw_exception() {
        // Créer un abonné n'ayant pas tout les params
        SubscriberDto subscriberWithoutAllParams = new SubscriberDto();
        subscriberWithoutAllParams.setFname("Bilel");
        subscriberWithoutAllParams.setLname("Maalej");
        // Vérifier qu'une exception est levée lorsque on veut créer cet abonnée (On doit fournir tout les parametres)
        assertThrows(AllSubscriberParmatersSHouldBeProvidedException.class, () -> subscriberService.createSubscriber(subscriberWithoutAllParams));
    }

    @Test
    public void test_id_should_throw_exception() {
        // Créer un abonné ayant un ID
        SubscriberDto subscriberWithId = new SubscriberDto();
        subscriberWithId.setFname("Bilel");
        subscriberWithId.setLname("Maalej");
        subscriberWithId.setId(1L);
        // Vérifier qu'une exception est levée lorsque on veut créer cet abonnée (Id ne doit pas etre renseigné)
        assertThrows(IdShouldNotBeProvidedException.class, () -> subscriberService.createSubscriber(subscriberWithId));
    }


}
