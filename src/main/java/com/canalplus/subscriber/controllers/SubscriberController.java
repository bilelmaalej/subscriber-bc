package com.canalplus.subscriber.controllers;

import com.canalplus.subscriber.dtos.SubscriberDto;
import com.canalplus.subscriber.models.SubscriberCriteria;
import com.canalplus.subscriber.services.SubscriberService;
import com.canalplus.subscriber.utils.Constants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ControllerAdvice
@RequestMapping(Constants.BASE_URL)
public class SubscriberController {
    private final SubscriberService subscriberService;

    @Autowired
    public SubscriberController(SubscriberService subscriberService) {

        this.subscriberService = subscriberService;
    }

    /**
     * Créaer un abonné (Id généré automatiquement)
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
     * Rechercher un abonnée avec un/des critères de recherche
     * @param criteria
     * @return
     */
    @GetMapping(Constants.SEARCH)
    public ResponseEntity<List<SubscriberDto>> searchSubscriber(@RequestBody SubscriberCriteria criteria) {
        List<SubscriberDto> subscribers = subscriberService.searchSubscriber(criteria);
        return ResponseEntity.ok(subscribers);
    }

    /**
     * Résilier un abonnée (le désactivé)
     * @param id
     * @return
     */
    @PatchMapping(Constants.cancel)
    public ResponseEntity<String> cancelSubscriber(@PathVariable Long id) {
        subscriberService.cancelSubscriber(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Résiliation de l'abonné avec succes");
    }

    /**
     * Mettre à jour un abonnée
     * @param subscriberDto
     * @return
     */
    @PutMapping(Constants.UPDATE)
    public ResponseEntity<String> updateSubscriber(@RequestBody SubscriberDto subscriberDto,
                                                   @PathVariable("id") Long subscriberId) {
        subscriberService.updateSubscriber(subscriberDto, subscriberId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Mise à jour de l'abonné avec succes");
    }

    /**
     * Retourner tout les abonnées
     * @return
     */
    @GetMapping(Constants.FIND_ALL)
    public List<SubscriberDto> findAll() {
        return subscriberService.findAll();
    }
}
