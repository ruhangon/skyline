package br.com.rdpg.skyline.controller.form;

import javax.validation.constraints.NotNull;

public class ContaDeInvestimentoForm {
	@NotNull(message = "campo cpf do usuário não pode ser nulo")
	private String cpfUsuario;

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}

}
