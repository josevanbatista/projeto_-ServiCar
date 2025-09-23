package application;

import java.io.IOException;
import java.sql.Connection;

import ConnectionFactory.ConnectionDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	private static Stage stage;
	private static Scene main;

	@Override
	public void start(Stage primaryStage) {
		try {

			stage = primaryStage;
			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/viewLogin.fxml"));
			main = new Scene(fxmlLogin);

			stage.setTitle("Tela de login");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/carro-limpo.png")));

			primaryStage.setScene(main);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void TelaLogin() throws IOException {
		FXMLLoader fxmlLogin = new FXMLLoader();
		fxmlLogin.setLocation(Main.class.getResource("/view/viewLogin.fxml"));
		Parent TelaLogin = fxmlLogin.load();
		main = new Scene(TelaLogin);
		stage.setTitle("ServiCar - Tela De Login");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

	public static void TelaHome() throws IOException {
		FXMLLoader fxmlHome = new FXMLLoader();
		fxmlHome.setLocation(Main.class.getResource("/view/viewHome.fxml"));
		Parent TelaHome = fxmlHome.load();
		main = new Scene(TelaHome);
		stage.setTitle("ServiCar - Menu Principal");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void TelaCadastroServico() throws IOException {
		FXMLLoader fxmlCadastroServico = new FXMLLoader();
		fxmlCadastroServico.setLocation(Main.class.getResource("/view/viewCadastroDeServicos.fxml"));
		Parent CadastroServico = fxmlCadastroServico.load();
		main = new Scene(CadastroServico);
		stage.setTitle("ServiCar - Casdastro de Serviços");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void TelaCadastroPrestadores() throws IOException {
		FXMLLoader fxmlCadastroPrestadores= new FXMLLoader();
		fxmlCadastroPrestadores.setLocation(Main.class.getResource("/view/viewCadastroPrestadores.fxml"));
		Parent CadastroPrestadores = fxmlCadastroPrestadores.load();
		main = new Scene(CadastroPrestadores);
		stage.setTitle("ServiCar - Casdastro De Prestadores");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void TelaRegidstroDeServicos() throws IOException {
		FXMLLoader fxmlRegidstroDeServicos= new FXMLLoader();
		fxmlRegidstroDeServicos.setLocation(Main.class.getResource("/view/viewRegistroDeServicos.fxml"));
		Parent RegidstroDeServicos = fxmlRegidstroDeServicos.load();
		main = new Scene(RegidstroDeServicos);
		stage.setTitle("ServiCar - Casdastro De Prestadores");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}

	public static void main(String[] args) {

		launch(args);
	}
	
}
