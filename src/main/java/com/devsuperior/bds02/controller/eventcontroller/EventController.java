package com.devsuperior.bds02.controller.eventcontroller;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.services.eventservice.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @GetMapping
    public ResponseEntity<List<EventDTO>> findAll(){
        List<EventDTO> list=eventService.findAll();
        return ResponseEntity.ok().body(list);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> upDateCateriry(@PathVariable Long id, @RequestBody EventDTO eventDTO){
        eventDTO=eventService.upDate(id, eventDTO);
        return ResponseEntity.ok().body(eventDTO);
    }
}
