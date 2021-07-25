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
		BigDecimal saldoBrl = new BigDecimal("0.0");
		Usuario usuario = usuarioService.buscar(cpfUsuario);
		ContaDeInvestimento contaDeInvestimento = new ContaDeInvestimento(saldoBrl, usuario);
		return contaDeInvestimentoRepository.save(contaDeInvestimento);
	}

	public ContaDeInvestimento atualiza(Long id, BigDecimal valorBrl) {
		ContaDeInvestimento contaDeInvestimento = contaDeInvestimentoRepository.getOne(id);
		BigDecimal saldoNovo = contaDeInvestimento.getSaldoBrl().add(valorBrl);
		contaDeInvestimento.setSaldoBrl(saldoNovo);
		return contaDeInvestimento;
	}

	public Boolean existeConta(Long id) {
		Optional<ContaDeInvestimento> optional = contaDeInvestimentoRepository.findById(id);
		if (optional.isPresent())
			return true;
		return false;
	}

	public ContaDeInvestimento buscar(Long id) {
		Optional<ContaDeInvestimento> contaDeInvestimento = contaDeInvestimentoRepository.findById(id);
		return contaDeInvestimento.get();
	}

	public Boolean ePossivelAbrir(String cpfUsuario) {
		if (usuarioService.existeUsuario(cpfUsuario))
			return true;
		return false;
	}

}
