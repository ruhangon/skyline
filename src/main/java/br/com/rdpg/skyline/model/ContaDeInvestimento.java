package br.com.rdpg.skyline.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conta")
public class ContaDeInvestimento {
	@Id
	private Long id;
	private BigDecimal saldoBrl;

	@MapsId
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public ContaDeInvestimento() {
	}

	public ContaDeInvestimento(BigDecimal saldoBrl, Usuario usuario) {
		this.saldoBrl = saldoBrl;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getSaldoBrl() {
		return saldoBrl;
	}

	public void setSaldoBrl(BigDecimal saldoBrl) {
		this.saldoBrl = saldoBrl;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		ContaDeInvestimento other = (ContaDeInvestimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
