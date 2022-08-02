package br.com.inatel.quotationmanagement.service;

/**
 * Handling the stock not found exception
 * @author francisco.carvalho
 * @since 1.0
 */

public class StockNotFound extends RuntimeException {
	private static final long serialVersionUID = -1069751640613275796L;

	public StockNotFound(String stockId) {
		super( String.format("Stock [%s] não encontrada", stockId) );
	}

}
