package com.devsuperior.bds02.services;

import java.util.List;

import com.devsuperior.bds02.dto.CityDTO;

public interface CityService {

	List<CityDTO> findAll();
	
	CityDTO insert(CityDTO dto);
	
	void delete(Long id);
	
}
