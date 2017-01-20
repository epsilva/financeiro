package br.com.devpi.model;

public enum MesesEnum {
	
	JANEIRO(0, "Janeiro"),
	FEVEREIRO(1, "Fevereiro"),
	MARCO(2, "Marco"),
	ABRIL(3, "Abril"),
	MAIO(4, "Maio"),
	JUNHO(5, "Junho"),
	JULHO(6, "Julho"),
	AGOSTO(7, "Agosto"),
	SETEMBRO(8, "Setembro"),
	OUTUBRO(9, "Outubro"),
	NOVEMBRO(10, "Novembro"),
	DEZEMBRO(11, "Dezembro");
	
	int mes;
	String descricao;
	
	private MesesEnum(int mes, String descricao) {
		this.mes = mes;
		this.descricao = descricao;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static String getDescricaoById(int idMes){
		for(MesesEnum meses : MesesEnum.values()){
			if(meses.getMes() == idMes){
				return meses.getDescricao();
			}
		}
		return null;
	}
	

}
