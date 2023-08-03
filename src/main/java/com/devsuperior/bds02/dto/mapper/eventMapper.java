package com.devsuperior.bds02.dto.mapper;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;

public class eventMapper {

    public static EventDTO toDTO(final Event event) {
        return new EventDTO(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getUrl(),
                event.getCity().getId()
        );
    }

    public static Event toEntity(final EventDTO eventDTO) {
        return new Event();
    }
}
