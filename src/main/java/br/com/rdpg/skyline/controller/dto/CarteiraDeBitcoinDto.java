package br.com.rdpg.skyline.controller.dto;

import java.math.BigDecimal;

import br.com.rdpg.skyline.model.CarteiraDeBitcoin;

public class CarteiraDeBitcoinDto {
	private Long id;
	private BigDecimal saldoBtc;
	private BigDecimal totalDeBrlInvestido;
	private Long idConta;

	public CarteiraDeBitcoinDto(CarteiraDeBitcoin carteiraDeBitcoin) {
		this.id = carteiraDeBitcoin.getId();
		this.saldoBtc = carteiraDeBitcoin.getSaldoBtc();
		this.totalDeBrlInvestido = carteiraDeBitcoin.getTotalDeBrlInvestido();
		this.idConta = carteiraDeBitcoin.getContaDeInvestimento().getId();
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getSaldoBtc() {
		return saldoBtc;
	}

	public BigDecimal getTotalDeBrlInvestido() {
		return totalDeBrlInvestido;
	}

	public Long getIdConta() {
		return idConta;
	}

}
