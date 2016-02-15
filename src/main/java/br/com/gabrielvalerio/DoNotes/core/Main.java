package br.com.gabrielvalerio.DoNotes.core;

import java.util.concurrent.ExecutionException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application{
	
	private MainSystemTray mainSysTray;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Platform.setImplicitExit(false);
		mainSysTray = new MainSystemTray();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Mostra alertas no modelo padr�o do JavaFX
	 * @param message A mensagem do alerta
	 * @param title O titulo do alerta
	 * @param type O tipo de alerta (Constantes da enum AlertType)
	 * @return true se o tipo de alerta for de confirma��o e o usuario pressionar OK; false nos demais casos.
	 */
	public static boolean displayAlert(String message, String title, AlertType type) {
		AlertTask task = new AlertTask(title, message, type);
		Platform.runLater(task);
		try {
			return task.get().booleanValue();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return false;
		}
	}
}
