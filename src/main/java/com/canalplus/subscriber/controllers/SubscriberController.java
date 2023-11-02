package com.canalplus.subscriber.controllers;

import com.canalplus.subscriber.dtos.SubscriberDto;
import com.canalplus.subscriber.models.SubscriberCriteria;
import com.canalplus.subscriber.services.SubscriberService;
import com.canalplus.subscriber.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(Constants.BASE_URL)
public class SubscriberController {
    private final SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {

        this.subscriberService = subscriberService;
    }

    /**
     *
     * @param subscriberDto
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createSubscriber(@RequestBody @Valid SubscriberDto subscriberDto) {
        SubscriberDto createdSubscriber = subscriberService.createSubscriber(subscriberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ajout abonné avec succes, id = " + createdSubscriber.getId());
    }

    /**
     *
     * @param criteria
     * @return
     */
    @GetMapping(Constants.SEARCH)
    public ResponseEntity<List<SubscriberDto>> searchSubscriber(@RequestBody SubscriberCriteria criteria) {
        List<SubscriberDto> subscribers = subscriberService.searchSubscriber(criteria);
        return ResponseEntity.ok(subscribers);
    }

    /**
     *
     * @param id
     * @return
     */
    @PatchMapping(Constants.cancel)
    public ResponseEntity<String> cancelSubscriber(@PathVariable Long id) {
        subscriberService.cancelSubscriber(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Résiliation de l'abonné avec succes");
    }

    /**
     *
     * @param subscriberDto
     * @return
     */
    @PutMapping
    public ResponseEntity<String> updateSubscriber(@RequestBody SubscriberDto subscriberDto) {
        subscriberService.updateSubscriber(subscriberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour de l'abonné avec succes");
    }

    /**
     *
     * @return
     */
    @GetMapping(Constants.FIND_ALL)
    public List<SubscriberDto> findAll() {
        return subscriberService.findAll();
    }
}