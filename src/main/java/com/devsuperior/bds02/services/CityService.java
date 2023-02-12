package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	public List<CityDTO> findAll(){
		List<City> list = repository.findAll(Sort.by("name")); // Retorna ordenado por nome
		
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList()); //Percorre a lista e retorna um DTO;
	}
}

