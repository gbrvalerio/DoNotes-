package br.com.gabrielvalerio.DoNotes.core;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import br.com.gabrielvalerio.DoNotes.resources.Resources;
import javafx.scene.control.Alert.AlertType;

public class MainSystemTray {
	
	
	private SystemTray 			sysTray;
	private TrayIcon 			trayIcon;
	private static boolean 		initialized;
	
	
	public MainSystemTray() {
		checkIfSysTrayIsSupported();
		
		sysTray = SystemTray.getSystemTray();
		trayIcon = new TrayIcon(Resources.ICONE);
		
		PopupMenu popup = new PopupMenu();
		
		MenuItem menuI_newNote 	= new MenuItem("Nova Nota");
		MenuItem menuI_exit		= new MenuItem("Sair");
		
		menuI_exit.addActionListener((event) -> {
			if(Main.displayAlert("Tem certeza que deseja sair?", "Confirma��o", AlertType.CONFIRMATION))
				System.exit(0);
		});
		
		menuI_newNote.addActionListener((event) -> {
			Main.openNote();
		});
		
		popup.add(menuI_newNote);
		popup.addSeparator();
		popup.add(menuI_exit);
		
		trayIcon.setPopupMenu(popup);
		try {
			sysTray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isInitialized() {
		return initialized;
	}
	
	private void checkIfSysTrayIsSupported() {
		if(!SystemTray.isSupported()){
			Main.displayAlert("Seu sistema n�o possui as funcionalidades requeridas pelo programa. Saindo...", "Erro", AlertType.ERROR);
			System.exit(-1);
		}
	}

	public void displayNotification(String title, String message, TrayIcon.MessageType type){
		trayIcon.displayMessage(title, message, type);
	}
	
	public void setIcon(Image icon){
		trayIcon.setImage(icon);
	}
}
