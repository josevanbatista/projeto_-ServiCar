package controller;

import java.io.IOException;

import application.Main;
import dao.ServicoDao;

import java.util.HashSet;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Servico;

public class controllerCadastroServico {

	@FXML
	private Button btCadastrar;

	@FXML
	private Button btCadastroPrestadores;

	@FXML
	private Button btCadastroVeiculos;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btProcurar;

	@FXML
	private Button btRegistroServicos;

	@FXML
	private Button btSair;

	@FXML
	private DatePicker txtAnoDeFab;

	@FXML
	private TextField txtComissao;

	@FXML
	private TextField txtCorDoVeiculo;

	@FXML
	private DatePicker txtDataDoServico;

	@FXML
	private ChoiceBox<?> txtFormaDePGTO;

	@FXML
	private TextField txtMarcaDoVeiculo;

	@FXML
	private TextField txtModeloDoVeiculo;

	@FXML
	private TextField txtNomeDoPrestador;

	@FXML
	private TextField txtObservacao;

	@FXML
	private TextField txtPlacaDoVeiculo;

	@FXML
	private Text txtUser;

	@FXML
	private TextField txtValorDoServico;

	@FXML
	void actionCadastroPrestadores(ActionEvent event) throws IOException {
		Main.TelaCadastroPrestadores();
	}

	@FXML
	void actionCadastroVeiculos(ActionEvent event) throws IOException {
		Main.TelaCadastroServico();
	}

	@FXML
	void actionCadastrar(ActionEvent event) {
		if(txtPlacaDoVeiculo.getText().isEmpty() || txtValorDoServico.getText().isEmpty()
    			|| txtNomeDoPrestador.getValue() == null) {
    		Alert erro = new Alert(AlertType.ERROR);
    		erro.setTitle("Erro ao salvar!");
    		erro.setContentText("Erro! verifique se todas as informações foram "
    				+ "preenchidas e tente novamente!");
    		erro.show();
    	}else if(!validarPlaca(txtPlacaDoVeiculo.getText())) {
    		Alert erro = new Alert(AlertType.ERROR);
    		erro.setTitle("Erro ao salvar!");
    		erro.setContentText("Erro! verifique se o CPF é valido e "
    				+ " e tente novamente!");
    		erro.show();
    	}else {
    		Servico servico = new Servico();
    		ServicoDao servicoDAO = new ServicoDao();
    		
    		servico.setDescricao(txtObservacao.getText());
    		servico.setPrestador_id(txtNomeDoPrestador.getText());
    		servico.setData_servico(txtDataDoServico.getValue().toString());
    		servico.setForma_pagamento(txtFormaDePGTO.getValue().toString());
    		servico.setValor_comissao(txtValorDoServico.getText());
    		servico.setVeiculo_id(txtPlacaDoVeiculo.getText());
    		
    		if(controllerRegistroDeServicos. == null) {
				clienteDAO.create(cliente);
				Alert msg = new Alert(AlertType.INFORMATION);
				msg.setTitle("Sucesso!");
				msg.setContentText("Cliente cadastrado com sucesso!");
				msg.show();
				
				Stage stage = (Stage) btSalvar.getScene().getWindow();
				stage.close();
			}else {
				clienteDAO.update(cliente);
				Alert msg = new Alert(AlertType.INFORMATION);
				msg.setTitle("Sucesso!");
				msg.setContentText("Cliente atualizado com sucesso!");
				msg.show();
				controllerRelatorioClientes.clienteEditar = null;
				Stage stage = (Stage) btSalvar.getScene().getWindow();
				stage.close();
			}
    	}
	}
	
	public static boolean validarPlaca(String placa) {
		    Set<String> placasCadastradas = new HashSet<>();
		        placa = placa.toUpperCase().replaceAll("[^A-Z0-9]", "");  
		        if (!placa.matches("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")) {
		            return false;
		        }
		        if (placasCadastradas.contains(placa)) {
		            return false; 
		        }

		        placasCadastradas.add(placa);
		        return true;
        
    }

	@FXML
    void actionCancelar(ActionEvent event) {
		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();
    }

	@FXML
	void actionProcurar(ActionEvent event) {

	}

	@FXML
	void actionRegistroServicos(ActionEvent event) throws IOException {
		Main.TelaRegidstroDeServicos();
	}

	@FXML
	void actionSair(ActionEvent event) throws IOException {
		Main.TelaLogin();
	}
	
	

	


}
