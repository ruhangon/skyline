package br.com.rdpg.skyline.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carteira")
public class CarteiraDeBitcoin {
	@Id
	private Long id;
	private BigDecimal saldoBtc;
	private BigDecimal totalDeBrlInvestido;

	@ElementCollection
	private List<Transacao> transacoes = new ArrayList<>();

	@MapsId
	@OneToOne
	@JoinColumn(name = "conta_id")
	private ContaDeInvestimento contaDeInvestimento;

	public CarteiraDeBitcoin() {
	}

	public CarteiraDeBitcoin(ContaDeInvestimento contaDeInvestimento) {
		this.saldoBtc = BigDecimal.ZERO;
		this.totalDeBrlInvestido = BigDecimal.ZERO;
		this.contaDeInvestimento = contaDeInvestimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSaldoBtc() {
		return saldoBtc;
	}

	public void setSaldoBtc(BigDecimal saldoBtc) {
		this.saldoBtc = saldoBtc;
	}

	public BigDecimal getTotalDeBrlInvestido() {
		return totalDeBrlInvestido;
	}

	public void setTotalDeBrlInvestido(BigDecimal totalDeBrlInvestido) {
		this.totalDeBrlInvestido = totalDeBrlInvestido;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

	public ContaDeInvestimento getContaDeInvestimento() {
		return contaDeInvestimento;
	}

	public void setContaDeInvestimento(ContaDeInvestimento contaDeInvestimento) {
		this.contaDeInvestimento = contaDeInvestimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarteiraDeBitcoin other = (CarteiraDeBitcoin) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
