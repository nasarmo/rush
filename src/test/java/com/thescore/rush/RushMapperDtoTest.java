package com.thescore.rush;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thescore.rush.dto.RushDto;
import com.thescore.rush.model.Rush;
import com.thescore.rush.service.RushMapperDto;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RushMapperDtoTest {
	

	@Test
	public void testMapToDto() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<RushDto> rushes = mapper.readValue(new ClassPathResource("rushingDto.json").getInputStream(), new TypeReference<List<RushDto>>() {
		});
		RushMapperDto rushMapperDto = new RushMapperDto();
		List<Rush> rush = rushMapperDto.mapFromDto(rushes);
		assertEquals(rush.size(), 5);
		assertEquals(rush.get(0).getPlayer(), "Joe Banyard");
		assertEquals(rush.get(1).getYardsPerGame(), 1.7);
	}
	
	@Test
	public void testMapFromDto() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Rush> rushes = mapper.readValue(new ClassPathResource("rush.json").getInputStream(), new TypeReference<List<Rush>>() {
		});
		RushMapperDto rushMapperDto = new RushMapperDto();
		List<RushDto> rushDto = rushMapperDto.mapToDto(rushes);
		assertEquals(rushDto.size(), 1);
		assertEquals(rushDto.get(0).getPlayer(), "bruce wayne");
		assertEquals(rushDto.get(0).getYardsPerGame(), 7.0);
	}

}
