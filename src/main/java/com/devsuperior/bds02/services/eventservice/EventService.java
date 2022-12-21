package com.devsuperior.bds02.services.eventservice;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repository.EventRepository.EventRepository;
import com.devsuperior.bds02.repository.cityrepository.CityRepository;
import com.devsuperior.bds02.services.exception.ResourceNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CityRepository cityRepository;
    public EventService(EventRepository eventRepository, CityRepository cityRepository) {
        this.eventRepository = eventRepository;
        this.cityRepository = cityRepository;
    }
    @Transactional
    public List<EventDTO> findAll(){
        List<Event>list=eventRepository.findAll(Sort.by("name"));
        return list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
    }
    @Transactional
    public EventDTO upDate(Long id, EventDTO eventDTO){
        try {
            var event= eventRepository.getOne(id);
            copyDtoToEntity(eventDTO,event);
            eventRepository.save(event).getCity().setId(id);
            return new EventDTO(event);

        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id " + id + " not found :(");
        }
    }
    private void copyDtoToEntity(EventDTO eventDTO, Event event){
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setUrl(eventDTO.getUrl());

        //event.getCity().clear();
        /*
        for (CityDTO  cityDTO : eventDTO.getCityId()){
            City city=cityRepository.getOne(cityDTO.getId());
            event.getCity().add(city);
        }
         */

    }

}
