package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class controllerRegistroDeServicos {

	@FXML
	private Button btCadastrar;

	@FXML
	private Button btCadastroPrestadores;

	@FXML
	private Button btCadastroVeiculos;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btCancelar1;

	@FXML
	private Button btRegistroServicos;

	@FXML
	private Button btSair;

	@FXML
	private TableColumn<?, ?> columnData;

	@FXML
	private TableColumn<?, ?> columnDescricao;

	@FXML
	private TableColumn<?, ?> columnFormaPGTO;

	@FXML
	private TableColumn<?, ?> columnIndice;

	@FXML
	private TableColumn<?, ?> columnPlaca;

	@FXML
	private TableColumn<?, ?> columnPrestador;

	@FXML
	private TableColumn<?, ?> columnValor;

	@FXML
	private TableView<?> tableRegistro;

	@FXML
	private TextField txtNome;

	@FXML
	private Text txtUser;

	@FXML
	void actionCadastrar(ActionEvent event) {

	}

	@FXML
	void actionCadastroPrestadores(ActionEvent event) throws IOException {
		Main.TelaCadastroPrestadores();
	}

	@FXML
	void actionCadastroVeiculos(ActionEvent event) throws IOException {
		Main.TelaCadastroServico();
	}

	@FXML
	void actionCancelar(ActionEvent event) {

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
