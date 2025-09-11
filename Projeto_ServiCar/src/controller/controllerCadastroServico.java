package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

	}
	
	@FXML
    void actionCancelar(ActionEvent event) {

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
