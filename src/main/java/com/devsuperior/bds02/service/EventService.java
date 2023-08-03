package com.devsuperior.bds02.service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.dto.mapper.eventMapper;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.exception.ResourceNotFoundException;
import com.devsuperior.bds02.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    // private final CityService cityService;

    @Transactional
    public EventDTO updateEvent(final Long id,final EventDTO response) {
        final var event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        // final var city = cityService.getCityById(response.getCity().getId());

        event.setName(response.getName());
        event.setDate(response.getDate());
        event.setUrl(response.getUrl());
        event.setCity(new City(response.getCityId(), null));

        return eventMapper.toDTO(eventRepository.save(event));
    }
}
