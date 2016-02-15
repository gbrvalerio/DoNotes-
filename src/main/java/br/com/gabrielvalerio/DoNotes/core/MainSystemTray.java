package br.com.gabrielvalerio.DoNotes.core;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import br.com.gabrielvalerio.DoNotes.resources.Resources;
import javafx.scene.control.Alert.AlertType;

public class MainSystemTray {
	
	private SystemTray sysTray;
	private TrayIcon trayIcon;
	
	public MainSystemTray() {
		if(!SystemTray.isSupported()){
			Main.displayAlert("Seu sistema não possui as funcionalidades requeridas pelo programa. Saindo...", "Erro", AlertType.ERROR);
			System.exit(-1);
		}
		
		sysTray = SystemTray.getSystemTray();
		trayIcon = new TrayIcon(Resources.ICONE);
		
		PopupMenu popup = new PopupMenu();
		
		MenuItem menuI_newNote 	= new MenuItem("Nova Nota");
		MenuItem menuI_exit		= new MenuItem("Sair");
		
		menuI_exit.addActionListener((event) -> {
				try {
					if(Main.displayAlert("Tem certeza que deseja sair?", "Confirmação", AlertType.CONFIRMATION))
						System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
}
