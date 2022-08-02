package br.com.inatel.quotationmanagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
/**
 * Stock Entity
 * @author francisco.carvalho
 * @since 1.0
 */
@Entity
public class Stock {
	
	@Id
	private String id;
	
	private String stockId;
	
	@OneToMany(mappedBy = "stock")
	private List<Quote> quotes = new ArrayList<>();

	//construtores...
	public Stock() {}

	public Stock(String id, String stockId) {
		this.id = id;
		this.stockId = stockId;
	}
	

	public Stock(String stockId) {
		this.stockId = stockId;
	}
	
	//listeners...
	
	@PrePersist
	private void onPersist() {
		this.id = UUID.randomUUID().toString();
	}
	
	
	//acessores...
	public String getId() {
		return id;
	}

	public String getStockId() {
		return stockId;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public void setQuotes(Quote quote) {
		this.quotes.add(quote);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(id, other.id);
	}
	
}
