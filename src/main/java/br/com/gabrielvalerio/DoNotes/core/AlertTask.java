package br.com.gabrielvalerio.DoNotes.core;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertTask extends Task<Boolean>{

	private final String title;
	private final String message;
	private final AlertType type;
	
	public AlertTask(String title, String message, AlertType type) {
		this.title = title;
		this.message = message;
		this.type = type;
	}
	
	@Override
	protected Boolean call() throws Exception {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(message);
		
		if(alert.getAlertType() == AlertType.CONFIRMATION)
			return alert.showAndWait().get() == ButtonType.OK;
		else
			alert.showAndWait();
			return false;
	}

}
