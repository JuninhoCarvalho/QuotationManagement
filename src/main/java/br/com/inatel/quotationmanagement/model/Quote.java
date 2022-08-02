package br.com.inatel.quotationmanagement.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

/**
 * Quote Entity
 * @author francisco.carvalho
 * @since 1.0
 */

@Entity
public class Quote {

	@Id
	private String id;
	
	@ManyToOne
	@NotNull
	private Stock stock;
	
	@NotNull
	private LocalDate dataQuote;
	
	@NotNull
	private double valorQuote;

	
	public Quote() {}
	
	public Quote(Stock stock, LocalDate dataQuote, double valorQuote) {
		this.stock = stock;
		this.dataQuote = dataQuote;
		this.valorQuote = valorQuote;
	}
	
	@PrePersist
	private void onSave() {
		this.id = UUID.randomUUID().toString();
	}
	
	//acessress...
	
	public String getId() {
		return id;
	}

	public Stock getStock() {
		return stock;
	}

	public LocalDate getDataQuote() {
		return dataQuote;
	}

	public double getValorQuote() {
		return valorQuote;
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
		Quote other = (Quote) obj;
		return Objects.equals(id, other.id);
	}
	
}
