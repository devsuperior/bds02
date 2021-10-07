package com.devsuperior.bds02.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CityControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void findAllShouldReturnAllResourcesSortedByName() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/cities")
					.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$[0].name").value("Belo Horizonte"));
		result.andExpect(jsonPath("$[1].name").value("Belém"));
		result.andExpect(jsonPath("$[2].name").value("Brasília"));
	}
	
	@Test
	public void insertShouldInsertResource() throws Exception {

		CityDTO dto = new CityDTO(null, "Recife");
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/cities")
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.name").value("Recife"));
	}

	@Test
	public void deleteShouldReturnNoContentWhenIndependentId() throws Exception {		
		
		Long independentId = 5L;
		
		ResultActions result =
				mockMvc.perform(delete("/cities/{id}", independentId));
		
		
		result.andExpect(status().isNoContent());
	}

	@Test
	public void deleteShouldReturnNotFoundWhenNonExistingId() throws Exception {		

		Long nonExistingId = 50L;
		
		ResultActions result =
				mockMvc.perform(delete("/cities/{id}", nonExistingId));

		result.andExpect(status().isNotFound());
	}

	@Test
	@Transactional(propagation = Propagation.NEVER) 
	public void deleteShouldReturnBadRequestWhenDependentId() throws Exception {		

		Long dependentId = 1L;
		
		ResultActions result =
				mockMvc.perform(delete("/cities/{id}", dependentId));
				
		result.andExpect(status().isBadRequest());
	}
}
