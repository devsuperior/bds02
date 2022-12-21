package com.devsuperior.bds02.controller.citycontroller;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.services.cityservice.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping
    public ResponseEntity<List<CityDTO>> findAll(){
        List<CityDTO> list=cityService.findAll();
        return ResponseEntity.ok().body(list);
    }
    @PostMapping
    public ResponseEntity<CityDTO> save(@RequestBody CityDTO cityDTO){
        cityDTO=cityService.insert(cityDTO);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(cityDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(cityDTO);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> daletCategory(@PathVariable Long id){
        cityService.deletProduct(id);
        return ResponseEntity.noContent().build();
    }

}
