package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.exceptions.DataBaseException;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> cities = cityRepository.findAll(Sort.by("name"));
		return cities.stream().map(CityDTO::new).collect(Collectors.toList());
	}
	
	@Transactional
	public CityDTO insert(CityDTO cityDTO) {
		City city = new City();
		city.setName(cityDTO.getName());
		city = cityRepository.save(city);
		return new CityDTO(city);
	}

	public void delete(Long id){
		try {
			cityRepository.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataBaseException("erro");
		}catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id: " + id + " not found");
		}
	}
	
}
