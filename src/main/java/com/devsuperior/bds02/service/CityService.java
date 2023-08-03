package com.devsuperior.bds02.service;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.dto.mapper.cityMapper;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.exception.DatabaseException;
import com.devsuperior.bds02.exception.ResourceNotFoundException;
import com.devsuperior.bds02.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    @Transactional
    public List<CityDTO> getAllCities(PageRequest pageRequest) {
        return cityRepository.findAll(pageRequest)
                .stream()
                .map(cityMapper::toDTO)
                .collect(Collectors.toList());
    }

    public City getCityById(final Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public CityDTO saveCity(final City request) {
        return cityMapper.toDTO(cityRepository.save(request));
    }

    public void deleteCity(final Long id) {
        try {
            cityRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Resource not found");
        }
        catch (DatabaseException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}