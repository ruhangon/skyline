package br.com.rdpg.skyline.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.rdpg.skyline.btc.Bitcoin;
import br.com.rdpg.skyline.controller.dto.InvestimentoDto;
import br.com.rdpg.skyline.model.CarteiraDeBitcoin;
import br.com.rdpg.skyline.model.ContaDeInvestimento;
import br.com.rdpg.skyline.model.Transacao;
import br.com.rdpg.skyline.repository.CarteiraDeBitcoinRepository;

@Service
public class CarteiraDeBitcoinService {
	@Autowired
	private CarteiraDeBitcoinRepository carteiraDeBitcoinRepository;

	@Autowired
	private ContaDeInvestimentoService contaDeInvestimentoService;

	public CarteiraDeBitcoin comprar(Long id, BigDecimal valorBtc) {
		if (!existeCarteira(id)) {
			ContaDeInvestimento contaDeInvestimento = contaDeInvestimentoService.buscar(id);
			CarteiraDeBitcoin carteira = new CarteiraDeBitcoin(contaDeInvestimento);
			carteiraDeBitcoinRepository.save(carteira);
		}
		CarteiraDeBitcoin carteiraDeBitcoin = carteiraDeBitcoinRepository.getOne(id);
		BigDecimal compraDeBtcEmReais = valorBtc.multiply(pegarPrecoDoBitcoinEmBrl());
		BigDecimal saldoBrlAtual = contaDeInvestimentoService.pegarSaldoBrlAtual(id);
		if (saldoBrlAtual.compareTo(compraDeBtcEmReais) >= 0)
			carteiraDeBitcoin = adquirirBtc(id, carteiraDeBitcoin, valorBtc, compraDeBtcEmReais);
		return carteiraDeBitcoin;
	}

	public CarteiraDeBitcoin buscar(Long id) {
		Optional<CarteiraDeBitcoin> carteiraDeBitcoin = carteiraDeBitcoinRepository.findById(id);
		return carteiraDeBitcoin.get();
	}

	public Boolean ePossivelTentarComprar(Long id) {
		return contaDeInvestimentoService.existeConta(id);
	}

	public Boolean existeCarteira(Long id) {
		Optional<CarteiraDeBitcoin> carteiraDeBitcoin = carteiraDeBitcoinRepository.findById(id);
		return carteiraDeBitcoin.isPresent();
	}

	public CarteiraDeBitcoin adquirirBtc(Long id, CarteiraDeBitcoin carteiraDeBitcoin, BigDecimal valorBtc,
			BigDecimal compraDeBtcEmReais) {
		BigDecimal novoSaldoBtc = carteiraDeBitcoin.getSaldoBtc().add(valorBtc);
		carteiraDeBitcoin.setSaldoBtc(novoSaldoBtc);
		BigDecimal novoTotalDeBrlInvestido = carteiraDeBitcoin.getTotalDeBrlInvestido().add(compraDeBtcEmReais);
		carteiraDeBitcoin.setTotalDeBrlInvestido(novoTotalDeBrlInvestido);
		Transacao transacao = new Transacao(compraDeBtcEmReais, valorBtc);
		carteiraDeBitcoin.getTransacoes().add(transacao);
		contaDeInvestimentoService.atualizarSaldoBrl(id, compraDeBtcEmReais);
		return carteiraDeBitcoin;
	}

	public BigDecimal pegarPrecoDoBitcoinEmBrl() {
		try {
			return Bitcoin.emBrl().getAmount();
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		}
		return BigDecimal.ZERO;
	}

	public InvestimentoDto preencherInformacoesDeInvestimento(Long id) {
		BigDecimal saldoBrl = contaDeInvestimentoService.pegarSaldoBrlAtual(id);
		CarteiraDeBitcoin carteiraDeBitcoin = buscar(id);
		BigDecimal saldoBtc = carteiraDeBitcoin.getSaldoBtc();
		BigDecimal totalDeBrlInvestido = carteiraDeBitcoin.getTotalDeBrlInvestido();
		BigDecimal precoDoBitcoinAgora = pegarPrecoDoBitcoinEmBrl();
		BigDecimal lucroTotal = calculaLucroAteOMomento(carteiraDeBitcoin, precoDoBitcoinAgora);
		List<Transacao> ultimasTransacoes = pegarUltimasCincoTransacoes(carteiraDeBitcoin);
		return new InvestimentoDto(id, saldoBrl, saldoBtc, totalDeBrlInvestido, lucroTotal, precoDoBitcoinAgora,
				ultimasTransacoes);
	}

	public BigDecimal calculaLucroAteOMomento(CarteiraDeBitcoin carteiraDeBitcoin, BigDecimal precoDoBitcoinAgora) {
		BigDecimal btcParaBrl = carteiraDeBitcoin.getSaldoBtc().multiply(precoDoBitcoinAgora);
		return btcParaBrl.subtract(carteiraDeBitcoin.getTotalDeBrlInvestido());
	}

	public List<Transacao> pegarUltimasCincoTransacoes(CarteiraDeBitcoin carteiraDeBitcoin) {
		if (carteiraDeBitcoin.getTransacoes().size() > 5) {
			List<Transacao> ultimasTransacoes = new ArrayList<>();
			int inicio = carteiraDeBitcoin.getTransacoes().size() - 5;
			int fim = carteiraDeBitcoin.getTransacoes().size();
			for (int i = inicio; i < fim; i++) {
				ultimasTransacoes.add(carteiraDeBitcoin.getTransacoes().get(i));
			}
			return ultimasTransacoes;
		}
		return carteiraDeBitcoin.getTransacoes();
	}

}
