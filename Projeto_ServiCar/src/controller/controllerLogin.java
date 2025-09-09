package controller;

import java.io.IOException;

import application.Main;
import dao.FuncionarioDAO;
import dao.PrestadorDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Funcionario;
import model.Prestador;
import javafx.scene.control.Alert.AlertType;

public class controllerLogin {

	@FXML
	private Button btEntrar;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private TextField txtUsuario;

	static Prestador prestador = new Prestador();
	PrestadorDao prestaDAO = new PrestadorDao();

	@FXML
	void actionLogin(ActionEvent event) throws IOException {
		String user = txtUsuario.getText();
		String password = txtSenha.getText();
		prestador = prestaDAO.autenticarUser(user, password);
		if (!prestador.equals(null)) {
			if (user.equals("") || password.equals("")) {
				Alert erro = new Alert(AlertType.ERROR);
				erro.setTitle("Erro de Login !");
				erro.setHeaderText("Falha ao Tentar Realizar Login");
				erro.setContentText("Verifique as informações e tente novamente !");
				erro.show();
			} else if (prestador.getCpf_cnpj().equals(user) && prestador.get().equals(password)) {
				Alert saudacao = new Alert(AlertType.INFORMATION);
				saudacao.setTitle("Seja Bem Vindo !");
				saudacao.setHeaderText(" bem-vindo de colta !");
				saudacao.setContentText("Ola! seja bem-vindo " + funcionario.getNomeFuncionario() + "!");
				saudacao.show();
				Main.TelaHome();
			}
		} else {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro de Login !");
			erro.setHeaderText("Falha ao Tentar Realizar Login");
			erro.setContentText("Verifique as informações e tente novamente !");
			erro.show();
		}

	}

}
