package br.com.gabrielvalerio.DoNotes.core;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import br.com.gabrielvalerio.DoNotes.resources.Resources;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
	 * Mostra alertas no modelo padrão do JavaFX
	 * @param message A mensagem do alerta
	 * @param title O titulo do alerta
	 * @param type O tipo de alerta (Constantes da enum AlertType)
	 * @return Caso o tipo de alerta selecionado seja de confirmação esse metodo retorna o objeto ButtonType OK ou CANCEL ou null se não for um alerta de confirmação
	 */
	public static ButtonType displayAlert(String message, String title, AlertType type){
		Alert alert = new Alert(type);
		
		alert.setTitle(title);
		alert.setContentText(message);
		
		if(alert.getAlertType() == AlertType.CONFIRMATION)
			return alert.showAndWait().get();
		else
			alert.show();
		return null;
	}
}
