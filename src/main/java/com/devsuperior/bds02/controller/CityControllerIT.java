package com.devsuperior.bds02.controller;

import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityControllerIT {
    private final CityService cityService;

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City saveCity(@RequestBody final City request) {
        return cityService.saveCity(request);
    }


}
