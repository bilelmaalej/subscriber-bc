package com.canalplus.subscriber;//package com.canalplus.subscriberbc;

import com.canalplus.subscriber.dtos.SubscriberDto;
import com.canalplus.subscriber.exceptions.AllSubscriberParmatersSHouldBeProvidedException;
import com.canalplus.subscriber.exceptions.IdShouldNotBeProvidedException;
import com.canalplus.subscriber.mappers.SubscriberMapper;
import com.canalplus.subscriber.repositories.SubscriberRepository;
import com.canalplus.subscriber.services.SubscriberServiceImpl;
import com.canalplus.subscriber.utils.Utils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SubscriberCancelServiceTest {
    @Mock
    private SubscriberRepository subscriberRepository;
    @Mock
    private SubscriberMapper subscriberMapper;
    @InjectMocks
    private SubscriberServiceImpl subscriberService;

    @Test
    public void test_correct_cancel_ubscriber() {
        Mockito.when(subscriberRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(Utils.createSubscriber()));
        // Given subscriber
        SubscriberDto expectedSubscriberDto = Utils.createSuscriberDto();
//        Mockito.doNothing().when(subscriberRepository).cancelSubscriber(1L);
        Mockito.doNothing().when(subscriberService).cancelSubscriber(1L);
        // Vérifier le seul abonnée dans la base est résilié
        assertFalse(subscriberService.findAll().stream().findFirst().get().isActive());
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
