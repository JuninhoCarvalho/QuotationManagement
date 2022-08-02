package br.com.inatel.quotationmanagement.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.inatel.quotationmanagement.adapter.StockManagerAdapter;
import br.com.inatel.quotationmanagement.controller.dto.StockManagerDto;
import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.repository.QuoteRepository;
import br.com.inatel.quotationmanagement.repository.StockRepository;

/**
 * Service class where the methods to be called in the Controller class are located
 * @author francisco.carvalho
 * @since 1.0
 */

@Service
@Transactional
public class StockService {

	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	QuoteRepository quoteRepository;

	@Autowired
	StockManagerAdapter stockManagerAdapter;
	
	/**
	 * 
	 * @return All stocks with your quotes
	 */
	
	@Cacheable(value = "stocksList")
	public List<Stock> findAllWithQuotes(){
		List<Stock> stocks = stockRepository.findAll();
		stocks.forEach(s -> s.getQuotes().size());
		return stocks;
	}
	
	/**
	 * 
	 * @return One stock find by your stockID
	 */
	
	public Optional<Stock> findOneWithQuotes(String stockId){
		Stock stock = stockRepository.findByStockId(stockId);
		if(stock != null) {
			stock.getQuotes().size();
			return Optional.of(stock);
		}
		return Optional.empty();
	}
	
	/**
	 * 
	 * Save the quotes on repository
	 */
	
	@CacheEvict(value = "stocksList", allEntries = true)
	public void saveQuotes(List<Quote> quotes) {
		quotes.forEach(q -> quoteRepository.save(q));
	}
	
	/**
	 * 
	 * Save the stock on repository
	 */
	@CacheEvict(value = "stocksList", allEntries = true)
	public void save(Stock stock) {
		stockRepository.save(stock);
	}
	
	/**
	 * 
	 * Checks if the stock is valid in stock manager
	 */
	public boolean existsAtStockManager(String stockId) {
		
		List<StockManagerDto> stocksAtManager = stockManagerAdapter.listAll();
		
		return stocksAtManager.stream().anyMatch(s -> s.getId().equals(stockId));
	}

	@CacheEvict(value = "stocksList", allEntries = true)
	public void deletar() {
		System.out.println("Deletado!");	
	}
}
