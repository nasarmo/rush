package com.thescore.rush;


import com.thescore.rush.dto.RushResponse;
import com.thescore.rush.model.Filter;
import com.thescore.rush.model.Rush;
import com.thescore.rush.repository.RushRepository;
import com.thescore.rush.service.RushMapperDto;
import com.thescore.rush.service.RushService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RushServiceTest {
	
	@Mock
	RushRepository rushRepository;
	@Autowired
	RushMapperDto rushMapperDto;
	
	@Autowired
	RushService rushService;
	
	@Test
	public void testGetPlayers() {
		List<Rush> rushes = rushService.saveAllFromJson();
		assertEquals(rushes.size(), 5);
		assertEquals(rushes.get(0).getPlayer(), "Joe Banyard");
		assertEquals(rushes.get(1).getYardsPerGame(), 1.7);
	}
	
	@Test
	public void testGetFilters() {
		List<Filter> filters = rushService.getFilters();
		assertEquals(filters.size(), 4);
		assertEquals(filters.get(0).getLabel(), "totalYards");
		assertEquals(filters.get(1).getLabel(), "longest");
		assertEquals(filters.get(2).getLabel(), "touchDowns");
		assertEquals(filters.get(3).getLabel(), "none");
	}
	
	
	@Test
	public void testFilteredData() {
		Filter filter = Filter.TOTAL_YARDS;
		String order = "Asc";
		String player = "";
		Integer page = 0;
		Integer size = 2;
		RushResponse response = rushService.getFilterData(filter, order, player, page, size);
		assertEquals(response.getRushDtos().size(), 2);
		assertEquals(response.getSizeOfPlayers(), 2);
		assertEquals(response.getRushDtos().get(0).getPlayer(), "Charlie Whitehurst");
		assertEquals(response.getRushDtos().get(1).getPlayer(), "Breshad Perriman");
	}
	
}
