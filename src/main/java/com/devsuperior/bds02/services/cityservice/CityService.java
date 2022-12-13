package com.devsuperior.bds02.services.cityservice;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repository.cityrepository.CityRepository;
import com.devsuperior.bds02.services.exception.DataBaseException;
import com.devsuperior.bds02.services.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CityService {

    private final CityRepository cityRepository;
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
    @Transactional
    public List<CityDTO> findAll(){
        List<City>list=cityRepository.findAll(Sort.by("name"));
        return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
    }
    @Transactional
    public CityDTO insert(CityDTO cityDTO){
        var city=new City();
        city.setName(cityDTO.getName());
        cityRepository.save(city);
        return new CityDTO(city);

    }


    public void deletProduct(Long id){

        try {
            cityRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
          throw new ResourceNotFoundException("Id not found "+id);

        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation");
        }



    }
}
