package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class controllerHome implements Initializable {

	@FXML
	private Button btCadastroPrestadores;

	@FXML
	private Button btCadastroVeiculos;

	@FXML
	private Button btRegistroServicos;

	@FXML
	private Button btSair;

	@FXML
	private Text txtUser;

	@FXML
	void actionCadastroPrestadores(ActionEvent event) throws IOException {
		Main.TelaCadastroPrestadores();

	}

	@FXML
	void actionCadastroVeiculos(ActionEvent event) throws IOException {
		Main.TelaCadastroServico();
	}

	@FXML
	void actionRegistroServicos(ActionEvent event) throws IOException {
		Main.TelaRegidstroDeServicos();
	}

	@FXML
	void actionSair(ActionEvent event) throws IOException {
    	Alert msg = new Alert(AlertType.CONFIRMATION);
    	msg.setTitle("Sair so Sistema");
    	msg.setHeaderText("Deseja sair do sistema ?");
    	msg.setContentText("Você estar saindo do sistema. Clique em ok para confirmar.");
    	
    	Optional<ButtonType> resultado = msg.showAndWait();
    	
    	if(resultado.isPresent() && resultado.get() == ButtonType.OK){
    		Main.TelaLogin();
    	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtUser.setText(controllerLogin.prestador.getNome());	
		
	}

}
