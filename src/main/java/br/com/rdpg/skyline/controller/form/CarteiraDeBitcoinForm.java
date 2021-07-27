package br.com.rdpg.skyline.controller.form;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CarteiraDeBitcoinForm {
	@NotNull(message = "campo valor não pode ser nulo")
	@Positive(message = "valor precisa ser positivo")
	@Digits(integer = 10000000, fraction = 1, message = "o número máximo aceito de casas decimais é {fraction}")
	private BigDecimal valorBtc;

	public BigDecimal getValorBtc() {
		return valorBtc;
	}

	public void setValorBtc(BigDecimal valorBtc) {
		this.valorBtc = valorBtc;
	}

}
