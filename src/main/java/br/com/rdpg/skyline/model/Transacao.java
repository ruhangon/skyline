package br.com.rdpg.skyline.model;

import java.math.BigDecimal;

public class Transacao {
	private BigDecimal valorBrl;
	private BigDecimal btcAdquirido;

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
