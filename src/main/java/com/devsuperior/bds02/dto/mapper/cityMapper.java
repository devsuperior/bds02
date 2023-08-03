package com.devsuperior.bds02.dto.mapper;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class cityMapper {

    public static CityDTO toDTO(final City city) {
        return new CityDTO(city.getId(), city.getName());
    }

    public static City toEntity(final CityDTO cityDTO) {
        return new City(cityDTO.getId(), cityDTO.getName());
    }
}
