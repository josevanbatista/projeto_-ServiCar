package controller;

import java.io.IOException;

import application.Main;
import dao.RegistroDao;
import dao.ServicoDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Registro;
import model.Servico;

public class controllerRegistroDeServicos {

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
    	RegistroDao  registroDao  = new RegistroDao();
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

}
