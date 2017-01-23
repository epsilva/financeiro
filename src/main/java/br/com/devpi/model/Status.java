package br.com.devpi.model;

public enum Status {
	
	PENDENTE("Pendente"),
	PAGO("Pago"),
	CANCELADO("Cancelado");
	
	private String descricao;
	
	Status(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}
