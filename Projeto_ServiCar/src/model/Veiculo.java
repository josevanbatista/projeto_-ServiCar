package model;

public class Veiculo {
	
	private String id;
	private String placa;
	private String marca;
	private String modelo;
	private String cor;
	private String ano;
	private String observacaes;
	
	
	public Veiculo(String id, String placa, String marca, String modelo, String cor, String ano, String observacao) {
		super();
		this.id = id;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.ano = ano;
		this.observacaes = observacao;
	}
//dfdfdfdfdd
	
	

	public String getId() {
		return id;
	}



	public Veiculo() {
	super();
}
	public void setId(String id) {
		this.id = id;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public String getAno() {
		return ano;
	}


	public void setAno(String ano) {
		this.ano = ano;
	}


	public String getObservacao() {
		return observacaes;
	}


	public void setObservacao(String observacao) {
		this.observacaes = observacao;
	}
	
	
	
	
	

}
