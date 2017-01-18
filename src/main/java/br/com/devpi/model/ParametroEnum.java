package br.com.devpi.model;

public enum ParametroEnum {
	
	ANO_2017("2017"),
	ANO_2018("2018");
	
	private String ano;
	
	private ParametroEnum(String ano) {
		this.ano = ano;
	}
	
	public String getAno(){
		return ano;
	}

}
