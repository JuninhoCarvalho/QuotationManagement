package br.com.inatel.quotationmanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.inatel.quotationmanagement.controller.dto.StockQuotesDto;
import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.service.StockService;

/**
 * Unit tests for the Service class
 * @author francisco.carvalho
 * @since 1.0
 */

@SpringBootTest
@ActiveProfiles("test")
class StockServiceTest {
	
	@Autowired
	private StockService stockService;

	/**
	 * Test method of how the return of a stock should be when listed all
	 */
	@Test
	public void givenGetAllStock_whenGetAllWithQuotes_shouldReturnStockQuoteDtoList() {
		List<Stock> stockQuotes = stockService.findAllWithQuotes();
		List<StockQuotesDto> stockQuotesDto = StockQuotesDto.converter(stockQuotes);

		assertEquals(stockQuotesDto.isEmpty(), false);
		assertEquals(stockQuotesDto.size(), 2);
		assertEquals(stockQuotesDto.get(0).getStockId(), "petr4");
		assertEquals(stockQuotesDto.get(0).getQuotesMap().size(), 2);
	}
	

	/**
	 * Test method of what the return of a stock should look like when searched for by stockId
	 */
	@Test
	public void givenGetStockByValidStockId_whenGetStockById_shouldReturnStockQuoteDto() {
		List<Stock> stockQuotes = new ArrayList<>();
		Optional<Stock> stockOpt = stockService.findOneWithQuotes("petr4");
	
		stockQuotes.add(stockOpt.get());
		List<StockQuotesDto> stockQuotesDto = StockQuotesDto.converter(stockQuotes);
		
		assertEquals(stockQuotesDto.isEmpty(), false);
		assertEquals(stockQuotesDto.size(), 1);
		assertEquals(stockQuotesDto.get(0).getStockId(), "petr4");
		assertEquals(stockQuotesDto.get(0).getQuotesMap().size(), 2);
	}
	
}
