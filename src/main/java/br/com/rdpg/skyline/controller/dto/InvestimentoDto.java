package br.com.rdpg.skyline.controller.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.rdpg.skyline.model.Transacao;

public class InvestimentoDto {
	private Long id;
	private BigDecimal saldoBrl;
	private BigDecimal saldoBtc;
	private BigDecimal totalDeBrlInvestido;
	private BigDecimal lucroAteOMomento;
	private BigDecimal precoDoBitcoinAgora;
	private List<Transacao> ultimasCincoTransacoes;

	public InvestimentoDto(Long id, BigDecimal saldoBrl, BigDecimal saldoBtc, BigDecimal totalDeBrlInvestido,
			BigDecimal lucroAteOMomento, BigDecimal precoDoBitcoinAgora, List<Transacao> ultimasCincoTransacoes) {
		this.id = id;
		this.saldoBrl = saldoBrl;
		this.saldoBtc = saldoBtc;
		this.totalDeBrlInvestido = totalDeBrlInvestido;
		this.lucroAteOMomento = lucroAteOMomento;
		this.precoDoBitcoinAgora = precoDoBitcoinAgora;
		this.ultimasCincoTransacoes = ultimasCincoTransacoes;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getSaldoBrl() {
		return saldoBrl;
	}

	public BigDecimal getSaldoBtc() {
		return saldoBtc;
	}

	public BigDecimal getTotalDeBrlInvestido() {
		return totalDeBrlInvestido;
	}

	public BigDecimal getLucroAteOMomento() {
		return lucroAteOMomento;
	}

	public BigDecimal getPrecoDoBitcoinAgora() {
		return precoDoBitcoinAgora;
	}

	public List<Transacao> getUltimasCincoTransacoes() {
		return ultimasCincoTransacoes;
	}

}
