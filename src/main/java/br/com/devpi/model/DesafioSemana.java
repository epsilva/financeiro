package br.com.devpi.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class DesafioSemana {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull(message = "Date de deposito é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataDeposito;
	
	@Column
	@NotNull(message = "valor depositado é obrigatório")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorDepositado;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDataDeposito() {
		return dataDeposito;
	}

	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}

	public BigDecimal getValorDepositado() {
		return valorDepositado;
	}

	public void setValorDepositado(BigDecimal valorDepositado) {
		this.valorDepositado = valorDepositado;
	}
	
	
	
}
