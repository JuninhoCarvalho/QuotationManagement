package br.com.inatel.quotationmanagement.controller.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.Stock;

/**
 * Data transfer object for Stock and your Quotes
 * @author francisco.carvalho
 * @since 1.0
 */

public class StockQuotesDto {
	
	private String id;
	private String stockId;
	private List<Quote> quotes = new ArrayList<>();
	private Map<LocalDate, Double> quotesMap = new HashMap<>();
	
	public StockQuotesDto(Stock stock) {
		this.id = stock.getId();
		this.stockId = stock.getStockId();
		this.quotes = stock.getQuotes();
		quotes.forEach(q -> quotesMap.put(q.getDataQuote(), q.getValorQuote()));
	}
	
	/**
	 * 
	 * @param stocks
	 * @return List of Stocks converted to StockQuotesDto
	 */
	
	public static List<StockQuotesDto> converter(List<Stock> stocks) {
		return stocks.stream().map(StockQuotesDto::new).collect(Collectors.toList());
	}
	
	public String getId() {
		return id;
	}
	
	public String getStockId() {
		return stockId;
	}
	
	public Map<LocalDate, Double> getQuotesMap() {
		return quotesMap;
	}
	
}
