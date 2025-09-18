package model;


// teste

public class Prestador {
	
	private String id;
	private String nome;
	private String cpf_cnpj;
	private String funcao;
	private String telefone;
	private String email;
	private Double comissao;
	private String observacoes;
	private String senha;
	public Object setId;
	
	
	public Prestador(String id, String nome, String cpf_cnpj, String funcao, String telefone, String email,
			Double comissao, String observacoes, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf_cnpj = cpf_cnpj;
		this.funcao = funcao;
		this.telefone = telefone;
		this.email = email;
		this.comissao = comissao;
		this.observacoes = observacoes;
		this.senha = senha;
	}
	

	public String getSenha() {
		return senha;
	}


	public Prestador() {
		super();
	}


	public void setSenha(String senha) {
		this.senha = senha;

	}








	public String getId() {
		return id;
	}


	public void setId(String i) {
		this.id = i;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf_cnpj() {
		return cpf_cnpj;
	}


	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}


	public String getFuncao() {
		return funcao;
	}


	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	public double getComissao() {
	    return comissao;
	}

	public void setComissao(double comissao) {
	    this.comissao = comissao;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	public void txtAreaEspecializada(String text) {
		// TODO Auto-generated method stub
		
	}


	


	

	

	
	
}
