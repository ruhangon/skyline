package br.com.rdpg.skyline.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class AtualizacaoContaDeInvestimentoForm {
	@NotNull(message = "campo valor não pode ser nulo")
	@Positive(message = "valor precisa ser positivo")
	private BigDecimal valorBrl;

	public BigDecimal getValorBrl() {
		return valorBrl;
	}

	public void setValorBrl(BigDecimal valorBrl) {
		this.valorBrl = valorBrl;
	}

}
