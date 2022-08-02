package br.com.inatel.quotationmanagement.controller;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author francisco.carvalho
 * @since 1.0
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.quotationmanagement.controller.dto.StockQuotesDto;
import br.com.inatel.quotationmanagement.controller.form.StockForm;
import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.service.StockService;

/**
 * Controller class where the endpoints will be made
 * @author francisco.carvalho
 * @since 1.0
 */

@RestController
public class StockController {

	@Autowired
	StockService stockService;
	
	/**
	 * 
	 * @return All stocks and their quotes already entered
	 */
	
	@GetMapping("/stock")
	public List<StockQuotesDto> listAll() {
		List<Stock> stockQuotes = stockService.findAllWithQuotes();
		List<StockQuotesDto> stockQuotesDto = StockQuotesDto.converter(stockQuotes);
		return stockQuotesDto;
	}
	
	/**
	 * 
	 * @param stockId
	 * @return Stock searched by your stockId and your quotes
	 */
	
	@GetMapping("/stock/{stockId}") 
	public ResponseEntity<?> findOneByStockId(@PathVariable("stockId") String stockId) {
		Optional<Stock> stockOpt = stockService.findOneWithQuotes(stockId);
		if(stockOpt.isPresent()) {
			Stock stockQuote = stockOpt.get();
			StockQuotesDto stockQuotesDto = new StockQuotesDto(stockQuote);
			return new ResponseEntity<>(stockQuotesDto, HttpStatus.OK);
		}
		return new ResponseEntity<>("Stock Not Found", HttpStatus.NOT_FOUND );
		
	}
	
	/**
	 * 
	 * @param form
	 * @return ResponseEntity whether or not it was inserted into the database (Created or BadRequest)
	 */
	
	@PostMapping("/stock")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<StockQuotesDto> saveStock(@RequestBody StockForm form) {

		Optional<Stock> stocksOpt = stockService.findOneWithQuotes(form.getStockId());
		
		if(stocksOpt.isPresent()) {
			Stock stock = stocksOpt.get();
			form.addQuoteList(stock);
			stockService.saveQuotes(stock.getQuotes());
			return new ResponseEntity<>(new StockQuotesDto(stock), HttpStatus.CREATED);
		}
		else if(stockService.existsAtStockManager(form.getStockId())){
			Stock stock = form.converter();
			stockService.save(stock);
			form.addQuoteList(stock);
			stockService.saveQuotes(stock.getQuotes());
			return new ResponseEntity<>(new StockQuotesDto(stock), HttpStatus.CREATED);
		}
		
		 return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/stockcache")
	public void deletar() {
		stockService.deletar();
	}
	
}
