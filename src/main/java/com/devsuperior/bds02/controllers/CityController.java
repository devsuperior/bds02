package com.devsuperior.bds02.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	CityService cityService;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll(){
		return ResponseEntity.ok(cityService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto){
		dto = cityService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<CityDTO> delete(@PathVariable Long id){
		cityService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
