package com.devsuperior.bds02.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private CityRepository cityRepository;

	@Transactional
	public EventDTO update(Long id, EventDTO eventDTO) {
		Event event = eventRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ID: " + id + " not found."));
		setEvent(eventDTO, event);
		return new EventDTO(eventRepository.save(event));
	}

	private void setEvent(EventDTO eventDTO, Event event) {
		event.setDate(eventDTO.getDate());
		event.setName(eventDTO.getName());
		event.setUrl(eventDTO.getUrl());
		event.setCity(cityRepository.findById(eventDTO.getCityId()).get());
	}

}
