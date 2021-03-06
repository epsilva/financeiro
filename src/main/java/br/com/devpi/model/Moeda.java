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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

/**
 * Classe para definir a quantidade de moedas depositadas.
 * @author esdraspinheiro
 */
@Entity
public class Moeda  implements Comparable<Moeda>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column
	private Integer umReal;
	
	@Column
	private Integer cinquentaCentavos;
	
	@Column
	private Integer vinteCincoCentavos;
	
	@Column
	private Integer dezCentavos;
	
	@Column
	private Integer cincoCentavos;
	
	@NotNull(message = "Date de vencimento é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataDeposito;
	
	@Transient
	private Double valorMoedasUmReal;
	
	@Transient
	private Double valorMoedasCinquentaCentavos;
	
	@Transient
	private Double valorMoedasVinteCincoCentavos;
	
	@Transient
	private Double valorMoedasDezCentavos;
	
	@Transient
	private Double valorMoedasCincoCentavos;
	
	@Transient
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorDepositado;
	
	@Transient
	private String dataDepositoMesAno;

	/**
	 * Retorna o codigo do deposito
	 * @return
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * Recebe o codigo do deposito
	 * @param codigo
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * Retorna a quantidade de moedas de um real
	 * @return
	 */
	public Integer getUmReal() {
		return umReal;
	}

	/**
	 * Recebe a quantidade de moedas de um real
	 * @param umReal
	 */
	public void setUmReal(Integer umReal) {
		this.umReal = umReal;
	}

	/**
	 * Retorna a quantidade de moedas de cinquenta centavos
	 * @return
	 */
	public Integer getCinquentaCentavos() {
		return cinquentaCentavos;
	}

	/**
	 * Recebe a quantidade de moedas de cinquenta centavos
	 * @param cinquentaCentavos
	 */
	public void setCinquentaCentavos(Integer cinquentaCentavos) {
		this.cinquentaCentavos = cinquentaCentavos;
	}

	/**
	 * Retorna a quantidade de moedas de vinte e cinco centavos
	 * @return
	 */
	public Integer getVinteCincoCentavos() {
		return vinteCincoCentavos;
	}

	/**
	 * Recebe a quantidade de moedas de vinte e cinco centavos
	 * @param vinteCincoCentavos
	 */
	public void setVinteCincoCentavos(Integer vinteCincoCentavos) {
		this.vinteCincoCentavos = vinteCincoCentavos;
	}

	/**
	 * Retorna a quantidade de moedas de dez centavos
	 * @return
	 */
	public Integer getDezCentavos() {
		return dezCentavos;
	}

	/**
	 * Recebe a quantidade de moedas de dez centavos
	 * @param dezCentavos
	 */
	public void setDezCentavos(Integer dezCentavos) {
		this.dezCentavos = dezCentavos;
	}

	/**
	 * Retorna a quantidade de moedas de cinco centavos
	 * @return
	 */
	public Integer getCincoCentavos() {
		return cincoCentavos;
	}

	/**
	 * Recebe a quantidade de moedas de 5 centavos
	 * @param cincoCentavos
	 */
	public void setCincoCentavos(Integer cincoCentavos) {
		this.cincoCentavos = cincoCentavos;
	}

	/**
	 * Recebe a data em que o deposito foi feito
	 * @return
	 */
	public Date getDataDeposito() {
		return dataDeposito;
	}

	/**
	 * Retorna a data do deposito
	 * @param dataDeposito
	 */
	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}
	
	/**
	 * Retorna soma dos valores das moedas de um real
	 * @return
	 */
	public Double getValorMoedasUmReal() {
		valorMoedasUmReal += getUmReal();
		return valorMoedasUmReal;
	}


	public Double getValorMoedasCinquentaCentavos() {
		return valorMoedasCinquentaCentavos;
	}

	public void setValorMoedasCinquentaCentavos(Double valorMoedasCinquentaCentavos) {
		this.valorMoedasCinquentaCentavos = valorMoedasCinquentaCentavos;
	}

	public Double getValorMoedasVinteCincoCentavos() {
		return valorMoedasVinteCincoCentavos;
	}

	public void setValorMoedasVinteCincoCentavos(Double valorMoedasVinteCincoCentavos) {
		this.valorMoedasVinteCincoCentavos = valorMoedasVinteCincoCentavos;
	}

	public Double getValorMoedasDezCentavos() {
		return valorMoedasDezCentavos;
	}

	public void setValorMoedasDezCentavos(Double valorMoedasDezCentavos) {
		this.valorMoedasDezCentavos = valorMoedasDezCentavos;
	}

	public Double getValorMoedasCincoCentavos() {
		return valorMoedasCincoCentavos;
	}

	public void setValorMoedasCincoCentavos(Double valorMoedasCincoCentavos) {
		this.valorMoedasCincoCentavos = valorMoedasCincoCentavos;
	}

	public BigDecimal getValorDepositado() {
		return valorDepositado;
	}

	public void setValorDepositado(BigDecimal valorDepositado) {
		this.valorDepositado = valorDepositado;
	}

	public String getDataDepositoMesAno() {
		return dataDepositoMesAno;
	}

	public void setDataDepositoMesAno(String dataDepositoMesAno) {
		this.dataDepositoMesAno = dataDepositoMesAno;
	}

	@Override
	public int compareTo(Moeda o) {
		return this.getDataDepositoMesAno().compareTo(o.getDataDepositoMesAno());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Moeda other = (Moeda) obj;
		if (dataDepositoMesAno == null) {
			if (other.dataDepositoMesAno != null) {
				return false;
			}
		} else if (!dataDepositoMesAno.equals(other.dataDepositoMesAno)) {
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
}
