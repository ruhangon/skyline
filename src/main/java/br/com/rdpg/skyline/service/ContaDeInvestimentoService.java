package br.com.rdpg.skyline.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rdpg.skyline.model.ContaDeInvestimento;
import br.com.rdpg.skyline.model.Usuario;
import br.com.rdpg.skyline.repository.ContaDeInvestimentoRepository;

@Service
public class ContaDeInvestimentoService {
	@Autowired
	private ContaDeInvestimentoRepository contaDeInvestimentoRepository;

	@Autowired
	private UsuarioService usuarioService;

	public ContaDeInvestimento inserir(String cpfUsuario) {
		BigDecimal saldoBrl = BigDecimal.ZERO;
		Usuario usuario = usuarioService.buscar(cpfUsuario);
		ContaDeInvestimento contaDeInvestimento = new ContaDeInvestimento(saldoBrl, usuario);
		return contaDeInvestimentoRepository.save(contaDeInvestimento);
	}

	public ContaDeInvestimento atualizar(Long id, BigDecimal valorBrl) {
		ContaDeInvestimento contaDeInvestimento = contaDeInvestimentoRepository.getOne(id);
		BigDecimal saldoNovo = contaDeInvestimento.getSaldoBrl().add(valorBrl);
		contaDeInvestimento.setSaldoBrl(saldoNovo);
		return contaDeInvestimento;
	}

	public Boolean existeConta(Long id) {
		Optional<ContaDeInvestimento> optional = contaDeInvestimentoRepository.findById(id);
		return optional.isPresent();
	}

	public ContaDeInvestimento buscar(Long id) {
		Optional<ContaDeInvestimento> contaDeInvestimento = contaDeInvestimentoRepository.findById(id);
		return contaDeInvestimento.get();
	}

	public Boolean ePossivelAbrir(String cpfUsuario) {
		return usuarioService.existeUsuario(cpfUsuario);
	}

	public BigDecimal pegarSaldoBrlAtual(Long id) {
		ContaDeInvestimento contaDeInvestimento = buscar(id);
		return contaDeInvestimento.getSaldoBrl();
	}

	public void atualizarSaldoBrl(Long id, BigDecimal valorBrl) {
		ContaDeInvestimento contaDeInvestimento = contaDeInvestimentoRepository.getOne(id);
		BigDecimal novoSaldoBrl = contaDeInvestimento.getSaldoBrl().subtract(valorBrl);
		contaDeInvestimento.setSaldoBrl(novoSaldoBrl);
	}

}
