package controller;

import java.io.IOException;
import java.net.URL;

import application.Main;
import dao.ServicoDao;
import dao.VeiculoDao;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Servico;
import model.Veiculo;

public class controllerCadastroServico implements Initializable {

	private static Object servicoEditar;

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
	private ChoiceBox<String> txtFormaDePGTO;
//sncocebve vjclw 
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
	    // Verifica campos obrigatórios
	    if (txtNomeDoPrestador.getText().isEmpty()
	            || txtPlacaDoVeiculo.getText().isEmpty()
	         //   || txtModeloDoVeiculo.getText().isEmpty()
	          //  || txtMarcaDoVeiculo.getText().isEmpty()
	            || txtValorDoServico.getText().isEmpty()
	            || txtComissao.getText().isEmpty()
	            || txtFormaDePGTO.getValue() == null
	            || txtDataDoServico.getValue() == null) {

	        Alert erro = new Alert(Alert.AlertType.ERROR);
	        erro.setTitle("Erro ao cadastrar!");
	        erro.setContentText("Por favor, preencha todos os campos obrigatórios!");
	        erro.show();
	        return;

	    } else if (!validarPlaca(txtPlacaDoVeiculo.getText())) {
	        Alert erro = new Alert(Alert.AlertType.ERROR);
	        erro.setTitle("Placa inválida");
	        erro.setContentText("A placa informada é inválida ou já cadastrada.");
	        erro.show();
	        return;
	    }

	    // Instanciar e preencher objeto Servico
	    Servico servico = new Servico();
	    ServicoDao servicoDao = new ServicoDao();

	    servico.setPrestador_id(txtNomeDoPrestador.getText());
	    Veiculo veiculo = new Veiculo();
	    VeiculoDao veiculoDAO = new VeiculoDao();
	    veiculo.setPlaca(txtPlacaDoVeiculo.getText());
	    veiculo = veiculoDAO.search(txtPlacaDoVeiculo.getText()).get(0);
	    
	    servico.setVeiculo_id(veiculo.getId());
	    servico.setModeloVeiculo(txtModeloDoVeiculo.getText());
	    servico.setModeloVeiculo(txtMarcaDoVeiculo.getText());
	    servico.setCorVeiculo(txtCorDoVeiculo.getText());
	    servico.setForma_pagamento(txtFormaDePGTO.getValue());
	    servico.setObservacao(txtObservacao.getText());

	    // Datas
	    servico.setDataServico(txtDataDoServico.getValue());
	    servico.setAnoFabricacao(txtAnoDeFab.getValue());

	    try {
	        double valor = Double.parseDouble(txtValorDoServico.getText().replace(",", "."));
	        double comissao = Double.parseDouble(txtComissao.getText().replace(",", "."));

	        servico.setValor_total(valor);
	        servico.setValor_comissao(comissao);
	    } catch (NumberFormatException e) {
	        Alert erro = new Alert(Alert.AlertType.ERROR);
	        erro.setTitle("Erro nos valores");
	        erro.setContentText("Valor do serviço ou comissão inválido(s). Use apenas números.");
	        erro.show();
	    }

	    // Verifica se é edição ou novo cadastro
	    if (controllerCadastroServico.servicoEditar == null) {
	        servicoDao.create(servico);

	        Alert msg = new Alert(Alert.AlertType.INFORMATION);
	        msg.setTitle("Sucesso!");
	        msg.setContentText("Serviço cadastrado com sucesso!");
	        msg.show();

	        Stage stage = (Stage) btCadastrar.getScene().getWindow();
	        stage.close();

	    } else {
	    	

	        servicoDao.update(servico);

	        Alert msg = new Alert(Alert.AlertType.INFORMATION);
	        msg.setTitle("Sucesso!");
	        msg.setContentText("Serviço atualizado com sucesso!");
	        msg.show();

	        controllerCadastroServico.servicoEditar = null;

	        Stage stage = (Stage) btCadastrar.getScene().getWindow();
	        stage.close();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtFormaDePGTO.getItems().addAll("Pix", "Dinheiro", "Cartão");
		txtFormaDePGTO.setValue("Dinheiro");

		txtNomeDoPrestador.setText(controllerLogin.prestador.getNome());
		txtNomeDoPrestador.setEditable(false);

		txtValorDoServico.setText("0,00");
		//txtValorDoServico.setEditable(false);
		txtComissao.setText("0,00");
		//txtComissao.setEditable(false);

		}

		
	}
	
	

	



