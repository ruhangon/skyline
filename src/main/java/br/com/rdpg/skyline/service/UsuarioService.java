package br.com.rdpg.skyline.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rdpg.skyline.model.Usuario;
import br.com.rdpg.skyline.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void inserir(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public Usuario buscar(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.get();
	}

	public Usuario buscar(String cpf) {
		Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);
		return usuario.get();
	}

	public Boolean existeUsuario(String cpf) {
		Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);
		if (usuario.isPresent())
			return true;
		return false;
	}

}
