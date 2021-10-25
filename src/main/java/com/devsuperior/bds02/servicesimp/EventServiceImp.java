package com.devsuperior.bds02.servicesimp;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.EventService;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventServiceImp implements EventService{

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Override
	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event event = eventRepository.getById(id);
			dto.setId(event.getId());
			eventRepository
			.save(new Event(dto.getId(),
					dto.getName(),
					dto.getDate(),
					dto.getUrl(),
					cityRepository.getById(dto.getCityId()))
					);
			return dto;
			
		} catch (JpaObjectRetrievalFailureException e) {
			throw new ResourceNotFoundException("Event not found for id: " + id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Event not found for id: " + id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Event not found for id: " + id);
		}
	}

}
