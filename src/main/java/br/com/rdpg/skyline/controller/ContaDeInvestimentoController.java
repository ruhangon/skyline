package br.com.rdpg.skyline.controller;

import java.math.BigDecimal;
import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.rdpg.skyline.controller.dto.ContaDeInvestimentoDto;
import br.com.rdpg.skyline.controller.form.AtualizacaoContaDeInvestimentoForm;
import br.com.rdpg.skyline.controller.form.ContaDeInvestimentoForm;
import br.com.rdpg.skyline.model.ContaDeInvestimento;
import br.com.rdpg.skyline.service.ContaDeInvestimentoService;

@RestController
@RequestMapping("/contas")
public class ContaDeInvestimentoController {
	@Autowired
	private ContaDeInvestimentoService contaDeInvestimentoService;

	@PostMapping
	@Transactional
	public ResponseEntity<ContaDeInvestimentoDto> abrir(@RequestBody @Valid ContaDeInvestimentoForm form,
			UriComponentsBuilder uriBuilder) {
		String cpfUsuario = form.getCpfUsuario();
		if (contaDeInvestimentoService.ePossivelAbrir(cpfUsuario)) {
			ContaDeInvestimento contaDeInvestimento = contaDeInvestimentoService.inserir(cpfUsuario);
			URI uri = uriBuilder.path("/contas/{id}").buildAndExpand(contaDeInvestimento.getId()).toUri();
			return ResponseEntity.created(uri).body(new ContaDeInvestimentoDto(contaDeInvestimento));
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ContaDeInvestimentoDto> transferir(@PathVariable Long id,
			@RequestBody @Valid AtualizacaoContaDeInvestimentoForm form) {
		if (contaDeInvestimentoService.existeConta(id)) {
			BigDecimal valorBrl = form.getValorBrl();
			ContaDeInvestimento contaDeInvestimento = contaDeInvestimentoService.atualizar(id, valorBrl);
			return ResponseEntity.ok(new ContaDeInvestimentoDto(contaDeInvestimento));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContaDeInvestimentoDto> buscar(@PathVariable Long id) {
		ContaDeInvestimento contaDeInvestimento = contaDeInvestimentoService.buscar(id);
		return ResponseEntity.ok(new ContaDeInvestimentoDto(contaDeInvestimento));
	}

}
