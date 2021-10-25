package com.devsuperior.bds02.servicesimp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.CityService;
import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class CityServiceImp implements CityService {

	@Autowired
	CityRepository cityRepository;
	
	@Override
	public List<CityDTO> findAll() {
		return cityRepository
				.findAll(Sort.by("name"))
				.stream()
				.map((city) -> new CityDTO(city))
				.collect(Collectors.toList());
	}

	@Override
	public CityDTO insert(CityDTO dto) {
		return new CityDTO(cityRepository.save(new City(null, dto.getName())));
	}

	@Override
	public void delete(Long id) {
		try {
			cityRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("City not found for id: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	 
}
