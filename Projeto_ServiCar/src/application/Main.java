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

	public static void main(String[] args) {

		launch(args);
	}
}
