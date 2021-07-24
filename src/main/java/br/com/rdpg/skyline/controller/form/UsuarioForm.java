package br.com.rdpg.skyline.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.rdpg.skyline.model.Usuario;

public class UsuarioForm {
	@NotBlank(message = "campo nome não pode estar em branco")
	private String nome;
	@NotNull(message = "campo cpf não pode ser nulo")
	private String cpf;

	public Usuario converter() {
		return new Usuario(nome, cpf);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
