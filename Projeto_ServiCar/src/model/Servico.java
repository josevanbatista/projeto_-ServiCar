package model;

import java.time.LocalDate;

public class Servico {

	private String id;
	private String veiculo_id;
	private String prestador_id;
	private String data_servico;
	private String descricao;
	private double valor_total;
	private double valor_comissao;
	private String forma_pagamento;
	private String comprovante_path;
	
	
	public Servico(String id, String veiculo_id, String prestador_id, String data_servico, String descricao,
			double valor_total, double valor_comissao, String forma_pagamento, String comprovante_path) {
		super();
		this.id = id;
		this.veiculo_id = veiculo_id;
		this.prestador_id = prestador_id;
		this.data_servico = data_servico;
		this.descricao = descricao;
		this.valor_total = valor_total;
		this.valor_comissao = valor_comissao;
		this.forma_pagamento = forma_pagamento;
		this.comprovante_path = comprovante_path;
	}


	public Servico() {
		// TODO Auto-generated constructor stub
	}


	public static Servico servicoEditar;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getVeiculo_id() {
		return veiculo_id;
	}


	public void setVeiculo_id(String veiculo_id) {
		this.veiculo_id = veiculo_id;
	}


	public String getPrestador_id() {
		return prestador_id;
	}


	public void setPrestador_id(String prestador_id) {
		this.prestador_id = prestador_id;
	}


	public String getData_servico() {
		return data_servico;
	}


	public void setData_servico(String data_servico) {
		this.data_servico = data_servico;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public double getValor_total() {
		return valor_total;
	}


	public void setValor_total(double valor) {
		this.valor_total = valor;
	}


	public double getValor_comissao() {
		return valor_comissao;
	}


	public void setValor_comissao(double comissao) {
		this.valor_comissao = comissao;
	}


	public String getForma_pagamento() {
		return forma_pagamento;
	}


	public void setForma_pagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}


	public String getComprovante_path() {
		return comprovante_path;
	}


	public void setComprovante_path(String comprovante_path) {
		this.comprovante_path = comprovante_path;
	}


	public void setModeloVeiculo(String text) {
		// TODO Auto-generated method stub
		
	}


	public void setCorVeiculo(String text) {
		// TODO Auto-generated method stub
		
	}


	public void setObservacao(String text) {
		// TODO Auto-generated method stub
		
	}


	public void setDataServico(LocalDate value) {
		// TODO Auto-generated method stub
		
	}


	public void setAnoFabricacao(LocalDate value) {
		// TODO Auto-generated method stub
		
	}



	
}
