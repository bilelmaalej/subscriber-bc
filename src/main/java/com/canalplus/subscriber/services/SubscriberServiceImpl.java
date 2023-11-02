package com.canalplus.subscriber.services;

import com.canalplus.subscriber.dtos.SubscriberDto;
import com.canalplus.subscriber.exceptions.*;
import com.canalplus.subscriber.mappers.SubscriberMapper;
import com.canalplus.subscriber.models.Subscriber;
import com.canalplus.subscriber.models.SubscriberCriteria;
import com.canalplus.subscriber.repositories.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    @Autowired
    private SubscriberRepository subscriberRepository;

    private final SubscriberMapper subscriberMapper = Mappers.getMapper(SubscriberMapper.class);

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public SubscriberDto createSubscriber(SubscriberDto subscriberDto) {
        // Valider les données de l'abonné
        // On ne doit pas renseigné l'ID de l'abonné
        if (subscriberDto.getId() != null) {
            throw new IdShouldNotBeProvidedException();
        }
        // Toutes les données doivent etre fournies
        if (subscriberDto.getFname() == null || subscriberDto.getLname() == null || subscriberDto.getMail() == null || subscriberDto.getPhone() == null) {
            throw new AllSubscriberParmatersSHouldBeProvidedException();
        }
        // Si l'abonné existe deja (On vérifie avec tous les donées)
        if (!getSubscriberByCriteria(new SubscriberCriteria(subscriberDto.getId(), subscriberDto.getFname(), subscriberDto.getLname(), subscriberDto.getMail(), subscriberDto.getPhone())).isEmpty()) {
            throw new SubscriberAlreadyExistsException();
        }
        // Marquer le nouvel abonné comme actif
        subscriberDto.setActive(true);
        // Mapper SubscriberDto to Entity
        Subscriber subscriber = subscriberMapper.toSubscriber(subscriberDto);
        return subscriberMapper.toSubscriberDto(subscriberRepository.save(subscriber));

    }

    @Override
    public List<SubscriberDto> getSubscriberByCriteria(SubscriberCriteria subscriberCriteria) {
        // Si aucun critère de recherche n'est renseigné
        if (Stream.of(subscriberCriteria.getMail(), subscriberCriteria.getPhone(), subscriberCriteria.getLname(), subscriberCriteria.getFname()).allMatch(Objects::isNull)) {
            throw new AtLeastOneCriteriaShouldBeShoosenException();
        }
        return subscriberMapper.toSubscriberDtoList(subscriberRepository.findByCriteria(subscriberCriteria));
    }

    @Override
    public List<SubscriberDto> searchSubscriber(SubscriberCriteria criteria) {
        List<SubscriberDto> subscribers = getSubscriberByCriteria(criteria);
        if (subscribers.isEmpty()) {
            throw new SubscriberNotFoundException();
        }
        return subscribers;
    }


    @Override
    public void cancelSubscriber(Long id) {
        Optional<Subscriber> subscriber = subscriberRepository.findById(id);
        if (subscriber.isPresent()) {
            subscriber.get().setActive(false);
            subscriberRepository.save(subscriber.get());
        } else {
            throw new SubscriberNotFoundException();
        }
    }

    @Override
    public SubscriberDto updateSubscriber(SubscriberDto subscriberDto) {
        // Si l'id n'est pas renseigné
        if (subscriberDto.getId() == null) {
            throw new IdShouldBeProvidedException();
        }
        Optional<Subscriber> subscriber = subscriberRepository.findById(subscriberDto.getId());
        // Si l'abonné n'existe pas
        if (!subscriber.isPresent()) {
            throw new SubscriberNotFoundException();
        }

        // Mapper SubscriberDto to Entity
        Subscriber subscriberEntity = subscriberMapper.toSubscriber(subscriberDto);
        return subscriberMapper.toSubscriberDto(subscriberRepository.save(subscriberEntity));
    }

    @Override
    public List<SubscriberDto> findAll() {
        return subscriberMapper.toSubscriberDtoList(subscriberRepository.findAll());
    }

//    @Override
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) {
//                return subscriberRepository.findById(username).orElseThrow(() -> new SubscriberNotFoundException());
//            }
//        };
//    }

}