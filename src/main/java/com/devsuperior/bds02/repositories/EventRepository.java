package com.devsuperior.bds02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.bds02.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

}
