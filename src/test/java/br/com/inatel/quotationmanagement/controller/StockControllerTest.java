package br.com.inatel.quotationmanagement.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import br.com.inatel.quotationmanagement.controller.form.StockForm;

/**
 * Unit tests for the Controller class
 * @author francisco.carvalho
 * @since 1.0
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class StockControllerTest {

	@Autowired
    private WebTestClient webTestClient;
	
	/**
	 * Should return 200 ok when listed all
	 */
	@Test
    void shouldReturn200OnListAll() {
        webTestClient.get()
        .uri("/stock")
        .exchange()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectStatus().isOk();
    }
	
	/**
	 * Should return 200 ok when listed one finded by stockId
	 */
    @Test
    void shouldReturn200OnFindByStockId() {
        webTestClient.get()
        .uri("/stock/petr4")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk();
    }
    
	/**
	 * Should return 404 when try to find one stock by a invalid stockID
	 */
    @Test
    void shouldReturn404WhenSearchingAInvalidStockId() {
        webTestClient.get()
        .uri("/stock/Invalid")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isNotFound();
    }
    
    /**
	 * Should return 201 when try to post a new Stock
	 */
    @Test
    void shouldReturn201WhenPostNewStockQuotes() {
        StockForm stockForm = createStockForm();
        webTestClient.post()
        .uri("/stock")
        .body(BodyInserters.fromValue(stockForm))
        .exchange()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectStatus().isCreated();
    }
    
    /**
	 * Method to create a valid form for testing
	 */
    private StockForm createStockForm() {
        Map<LocalDate, Double> quoteMap = new HashMap<>();
        LocalDate date = LocalDate.now();
        quoteMap.put(date, 50.0);
        quoteMap.put(date.plusDays(2), 75.0);
        StockForm stockForm = new StockForm("petr4", quoteMap);
        return stockForm;
    }

}
