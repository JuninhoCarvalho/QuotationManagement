package br.com.inatel.quotationmanagement.adapter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.inatel.quotationmanagement.controller.dto.StockManagerDto;
import br.com.inatel.quotationmanagement.controller.form.NotificationForm;
import reactor.core.publisher.Flux;

/**
 * Represents the adaptation with the Lucas Vilela api
 * @author francisco.carvalho
 * @since 1.0
 */

@Service
public class StockManagerAdapter {
	
	@Value("${URL_MANAGER}")
	private String URL_MANAGER;
	
	@Value("${server.host}")
	private String host;
	
	@Value("${server.port}")
	private String port;
	
	@Cacheable(value = "stocksAtManagerList")
	public List<StockManagerDto> listAll(){
		
		List<StockManagerDto> stocksAtManager = new ArrayList<>();
		
		Flux<StockManagerDto> flux = WebClient.create(URL_MANAGER)
				.get()
				.uri("/stock")
				.retrieve()
				.bodyToFlux(StockManagerDto.class);
		
		flux.subscribe(f -> stocksAtManager.add(f));
		flux.blockLast();
				
		return stocksAtManager;
	}
	
	@PostConstruct
	public void notificationRegister() {
		
		NotificationForm form = new NotificationForm(host,port);
		
		WebClient.create(URL_MANAGER)
			.post()
			.uri("/notification")
			.bodyValue(form)
			.retrieve()
			.toBodilessEntity()
			.block();
	}
}
