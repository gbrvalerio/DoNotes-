package br.com.gabrielvalerio.DoNotes.core;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import br.com.gabrielvalerio.DoNotes.resources.*;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

public class Main extends Application{
	
	private SystemTray sysTray;
	private TrayIcon trayIcon;
	
	private void setTrayUp(){
		if(!SystemTray.isSupported()){
			JOptionPane.showMessageDialog(null, "System Tray não suportado.", "Erro.", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		
		sysTray = SystemTray.getSystemTray();
		trayIcon = new TrayIcon(Resources.ICONE);
		
		PopupMenu popup = new PopupMenu();
		
		MenuItem menuI_newNote 	= new MenuItem("Nova Nota");
		MenuItem menuI_exit		= new MenuItem("Sair");
		
		menuI_exit.addActionListener((event) -> {
			if(JOptionPane.showConfirmDialog(null, "Certeza que deseja sair?", "Confirmação", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
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
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
