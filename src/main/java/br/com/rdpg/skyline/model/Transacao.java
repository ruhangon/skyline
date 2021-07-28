package br.com.rdpg.skyline.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Transacao {
	private BigDecimal valorBrl;
	private BigDecimal btcAdquirido;

	public Transacao() {
	}

	public Transacao(BigDecimal valorBrl, BigDecimal btcAdquirido) {
		this.valorBrl = valorBrl;
		this.btcAdquirido = btcAdquirido;
	}

	public BigDecimal getValorBrl() {
		return valorBrl;
	}

	public BigDecimal getBtcAdquirido() {
		return btcAdquirido;
	}

}
