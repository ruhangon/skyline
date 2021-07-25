package br.com.rdpg.skyline.controller.dto;

import java.math.BigDecimal;

import br.com.rdpg.skyline.model.ContaDeInvestimento;

public class ContaDeInvestimentoDto {
	private Long id;
	private BigDecimal saldoBrl;
	private Long idUsuario;
	private String nomeUsuario;

	public ContaDeInvestimentoDto(ContaDeInvestimento contaDeInvestimento) {
		this.id = contaDeInvestimento.getId();
		this.saldoBrl = contaDeInvestimento.getSaldoBrl();
		this.idUsuario = contaDeInvestimento.getUsuario().getId();
		this.nomeUsuario = contaDeInvestimento.getUsuario().getNome();
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getSaldoBrl() {
		return saldoBrl;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

}
