package br.com.inatel.quotationmanagement.controller.form;

/**
 * Data loaded from a stock post
 * @author francisco.carvalho
 * @since 1.0
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.Stock;

public class StockForm {
	
	private String stockId;
	private Map<LocalDate, Double> quotesMap = new HashMap<>();
	
	public StockForm() {}
	
	public StockForm(String stockId, Map<LocalDate, Double> quotesMap) {
		this.stockId = stockId;
		this.quotesMap = quotesMap;
	}

	public Map<LocalDate, Double> getQuotesMap() {
		return quotesMap;
	}

	public void setQuotesMap(Map<LocalDate, Double> quotesMap) {
		this.quotesMap = quotesMap;
	}
	
	public String getStockId() {
		return stockId;
	}
	
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	
	public Stock converter() {
		return new Stock(stockId);
	}
	
	/**
	 * 
	 * @param stock
	 * @return List of required stock quotes
	 */
	
	public List<Quote> addQuoteList(Stock stock){
		List<Quote> quotes = new ArrayList<>();
		quotesMap.forEach((d, p) -> {
			Quote quote = new Quote(stock, d,p);
			quotes.add(quote);
			stock.setQuotes(quote);
		});
		
		return stock.getQuotes();
	}

}
