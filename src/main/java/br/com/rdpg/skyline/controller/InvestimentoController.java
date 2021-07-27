package br.com.rdpg.skyline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rdpg.skyline.controller.dto.InvestimentoDto;
import br.com.rdpg.skyline.service.CarteiraDeBitcoinService;

@RestController
@RequestMapping("/investimentos")
public class InvestimentoController {
	@Autowired
	private CarteiraDeBitcoinService carteiraDeBitcoinService;

	@GetMapping("/{id}")
	public ResponseEntity<InvestimentoDto> buscar(@PathVariable Long id) {
		InvestimentoDto investimentoDto = carteiraDeBitcoinService.preencherInformacoesDeInvestimento(id);
		return ResponseEntity.ok(investimentoDto);
	}

}
