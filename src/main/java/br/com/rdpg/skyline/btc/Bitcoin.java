package br.com.rdpg.skyline.btc;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Bitcoin {
	private static RestTemplate restTemplate;

	@Autowired
	public Bitcoin(RestTemplate restTemplate) {
		Bitcoin.restTemplate = restTemplate;
	}

	private String base;
	private String currency;
	private BigDecimal amount;

	public Bitcoin(String base, String currency, BigDecimal amount) {
		this.base = base;
		this.currency = currency;
		this.amount = amount;
	}

	private static JsonNode pegarConteudoDoJson(String url, RestTemplate restTemplate) throws JsonProcessingException {
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(response.getBody());
	}

	public static Bitcoin emBrl() throws JsonProcessingException {
		JsonNode jsonNode = pegarConteudoDoJson("https://api.coinbase.com/v2/prices/spot?currency=BRL", restTemplate);
		String base = jsonNode.path("data").path("base").asText();
		String currency = jsonNode.path("data").path("currency").asText();
		BigDecimal amount = new BigDecimal(jsonNode.path("data").path("amount").asText());
		return new Bitcoin(base, currency, amount);
	}

	public String getBase() {
		return base;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

}
