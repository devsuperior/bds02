package com.devsuperior.bds02.service;

import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    @Transactional
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City saveCity(final City request) {
        return cityRepository.save(request);
    }
}
