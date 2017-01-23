package br.com.devpi.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Calendario implements Comparable<Calendario>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotEmpty(message = "Descrição é obrigatória")
	@Size(max = 60, message = "Descrição não pode conter mais de 60 caracteres")
	private String descricao;
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
	
	private String ano;
	
	private String mes;
	
	@NotNull(message = "Date de vencimento é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Transient
	private List<Calendario> listaCalendario;
	
	@NumberFormat(pattern = "#,##0.00")
	@Transient
	private BigDecimal valorTotalMes = new BigDecimal(0);
	
	@NumberFormat(pattern = "#,##0.00")
	@Transient
	private BigDecimal valorTotalPago = new BigDecimal(0);
	
	@NumberFormat(pattern = "#,##0.00")
	@Transient
	private BigDecimal valorTotalPendente = new BigDecimal(0);

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}
	
	public boolean isPago(){
		return Status.PAGO.equals(this.status);
	}
	
	public boolean isPendente(){
		return Status.PENDENTE.equals(this.status);
	}
	
	public boolean isCancelado(){
		return Status.CANCELADO.equals(this.status);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Calendario> getListaCalendario() {
		return listaCalendario;
	}

	public void setListaCalendario(List<Calendario> listaCalendario) {
		this.listaCalendario = listaCalendario;
	}

	public BigDecimal getValorTotalMes() {
		return valorTotalMes;
	}

	public void setValorTotalMes(BigDecimal valorTotalMes) {
		this.valorTotalMes = valorTotalMes;
	}
	
	public BigDecimal getValorTotalPago() {
		return valorTotalPago;
	}

	public void setValorTotalPago(BigDecimal valorTotalPago) {
		this.valorTotalPago = valorTotalPago;
	}

	public BigDecimal getValorTotalPendente() {
		return valorTotalPendente;
	}

	public void setValorTotalPendente(BigDecimal valorTotalPendente) {
		this.valorTotalPendente = valorTotalPendente;
	}

	@Override
	public int compareTo(Calendario o) {
		return this.getData().compareTo(o.getData());
	}
	
	
	
	
}
