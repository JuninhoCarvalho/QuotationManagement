package br.com.inatel.quotationmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inatel.quotationmanagement.model.Stock;

/**
 * Stock repository interface
 * @author francisco.carvalho
 * @since 1.0
 */

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {
	
	Stock findByStockId(String stockId);

	Stock getReferenceByStockId(String stockId);
}
