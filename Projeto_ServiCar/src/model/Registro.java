package model;

public class Registro {
	private String descricao;
	private String data;
	private String formaPGTO;
	private String placa;
	private String modelo;
	private double valor;
	private double comissao;

	public Registro(String descricao, String data, String formaPGTO, String placa, double valor, double comissao) {
		this.descricao = descricao;
		this.data = data;
		this.formaPGTO = formaPGTO;
		this.placa = placa;
		this.placa = modelo;
		this.valor = valor;
		this.comissao = comissao;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getData() {
		return data;
	}

	public String getFormaPGTO() {
		return formaPGTO;
	}

	public String getPlaca() {
		return placa;
	}

	public double getValor() {
		return valor;
	}

	public double getComissao() {
		return comissao;
	}
}
