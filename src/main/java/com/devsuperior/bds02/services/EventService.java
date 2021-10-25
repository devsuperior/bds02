package com.devsuperior.bds02.services;

import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.EventDTO;

@Service
public interface EventService {

	EventDTO update(Long id, EventDTO dto);
}
