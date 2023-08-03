package com.devsuperior.bds02.controller;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventControllerIT {
    private final EventService eventService;

    @PutMapping("/{id}")
    public EventDTO updateEvent(
            @PathVariable final Long id,
            @RequestBody final EventDTO request
    ) {
        return eventService.updateEvent(id, request);
    }
}
