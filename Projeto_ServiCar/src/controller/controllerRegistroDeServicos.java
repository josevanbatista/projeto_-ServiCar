package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import application.Main;
import dao.PrestadorDao;
import dao.RegistroDao;
import dao.ServicoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.Prestador;
import model.Registro;
import model.Servico;

public class controllerRegistroDeServicos implements Initializable {

	@FXML
	private Button btCadastroPrestadores;

	@FXML
	private Button btCadastroServicos;

	@FXML
	private Button btProcurar;

	@FXML
	private Button btRegistroServicos;

	@FXML
	private Button btSair;

	@FXML
	private TableColumn<Servico, String> columnData;

	@FXML
	private TableColumn<Servico, String> columnDescricao;

	@FXML
	private TableColumn<Servico, String> columnFormaPGTO;

	@FXML
	private TableColumn<Servico, String> columnIndice;

	@FXML
	private TableColumn<Servico, String> columnModelo;

	@FXML
	private TableColumn<Servico, String> columnComissao;

	@FXML
	private TableColumn<Servico, String> columnValor;

	@FXML
	private TableView<Registro> tableRegistro;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtTotaComissao;

	@FXML
	private TextField txtTotaVendidos;

	@FXML
	private Text txtUser;

	@FXML
	void actonCPFClick(MouseEvent event) {
		if (txtNome.getText().length() > 3) {
			PrestadorDao prestadorDao = new PrestadorDao();
			Prestador prestador = new Prestador();
			prestador.setNome(txtNome.getText());
			ArrayList<Prestador> prestadores = new ArrayList<>();
			prestadores = prestadorDao.search(prestador.getNome());
			prestador = prestadores.get(0);
			txtNome.setText(prestador.getNome());
		} else {
			txtNome.setText("");
		}
	}

	@FXML
	void actonCPFType(KeyEvent event) {
		if (txtNome.getText().length() > 3) {
			PrestadorDao prestadorDao = new PrestadorDao();
			Prestador prestador = new Prestador();
			prestador.setNome(txtNome.getText());
			ArrayList<Prestador> prestadores = new ArrayList<>();
			prestadores = prestadorDao.search(prestador.getNome());
			prestador = prestadores.get(0);
			txtNome.setText(prestador.getNome());
		} else {
			txtNome.setText("");
		}
	}

	@FXML
	void actionCadastroPrestadores(ActionEvent event) throws IOException {
		Main.TelaCadastroPrestadores();
	}

	@FXML
	void actionCadastroServicos(ActionEvent event) throws IOException {
		Main.TelaCadastroServico();
	}

	@FXML
	void actionProcurar(ActionEvent event) {
		String nomeFuncionario = txtNome.getText();

		ObservableList<Registro> resultados = RegistroDao.buscarPorFuncionario(nomeFuncionario);

		tableRegistro.setItems(resultados);
	}

	@FXML
	public void initialize() {
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
		columnFormaPGTO.setCellValueFactory(new PropertyValueFactory<>("formaPGTO"));
		columnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		columnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		columnComissao.setCellValueFactory(new PropertyValueFactory<>("comissao"));
	}

	private ObservableList<Registro> listaServicos;

	public void carregarTableRegistro() {
		RegistroDao registroDao = new RegistroDao();
		listaServicos = FXCollections.observableArrayList(listaServicos);

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		columnData.setCellValueFactory(new PropertyValueFactory<>("data_servico"));
		columnValor.setCellValueFactory(new PropertyValueFactory<>("valor_total"));
		columnFormaPGTO.setCellValueFactory(new PropertyValueFactory<>("forma_pagamento"));
		columnComissao.setCellValueFactory(new PropertyValueFactory<>("valor_comissao"));
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		tableRegistro.setItems(listaServicos);
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
		txtUser.setText(controllerLogin.prestador.getNome());
		PrestadorDao prestadorDao = new PrestadorDao();
		ArrayList<Prestador> arrayPrestador = prestadorDao.read();
		String[] listaPrestadores = new String[prestadorDao.read().size()];
		for (int i = 0; i < prestadorDao.read().size(); i++) {
			Prestador prestador = arrayPrestador.get(i);
			listaPrestadores[i] = prestador.getNome();
		}
		TextFields.bindAutoCompletion(txtNome, listaPrestadores).setOnAutoCompleted(event -> actonCPFClick(null));
	}
}
