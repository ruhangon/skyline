package br.com.rdpg.skyline.controller.dto;

import br.com.rdpg.skyline.model.Usuario;

public class UsuarioDto {
	private Long id;
	private String nome;
	private String cpf;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

}
