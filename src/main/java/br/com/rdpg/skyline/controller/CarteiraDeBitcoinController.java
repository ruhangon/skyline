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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.rdpg.skyline.controller.dto.CarteiraDeBitcoinDto;
import br.com.rdpg.skyline.controller.form.CarteiraDeBitcoinForm;
import br.com.rdpg.skyline.model.CarteiraDeBitcoin;
import br.com.rdpg.skyline.service.CarteiraDeBitcoinService;

@RestController
@RequestMapping("/carteiras")
public class CarteiraDeBitcoinController {
	@Autowired
	private CarteiraDeBitcoinService carteiraDeBitcoinService;

	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<CarteiraDeBitcoinDto> comprarBitcoin(@PathVariable Long id,
			@RequestBody @Valid CarteiraDeBitcoinForm form, UriComponentsBuilder uriBuilder) {
		if (carteiraDeBitcoinService.ePossivelTentarComprar(id)) {
			BigDecimal valorBtc = form.getValorBtc();
			CarteiraDeBitcoin carteiraDeBitcoin = carteiraDeBitcoinService.comprar(id, valorBtc);
			URI uri = uriBuilder.path("/carteiras/{id}").buildAndExpand(carteiraDeBitcoin.getId()).toUri();
			return ResponseEntity.created(uri).body(new CarteiraDeBitcoinDto(carteiraDeBitcoin));
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarteiraDeBitcoinDto> buscar(@PathVariable Long id) {
		CarteiraDeBitcoin carteiraDeBitcoin = carteiraDeBitcoinService.buscar(id);
		return ResponseEntity.ok(new CarteiraDeBitcoinDto(carteiraDeBitcoin));
	}

}
