package br.com.rdpg.skyline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rdpg.skyline.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
