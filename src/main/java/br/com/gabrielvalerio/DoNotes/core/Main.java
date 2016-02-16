package br.com.gabrielvalerio.DoNotes.core;

import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TrayIcon.MessageType;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.util.concurrent.ExecutionException;

import br.com.gabrielvalerio.DoNotes.resources.Resources;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	
	private static MainSystemTray 	mainSysTray;
	private static Stage			mainStage;
	private static boolean			initialized = false;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		//evita a thread do fx de fechar caso nao tenha nenhuma janela ainda
		Platform.setImplicitExit(false);
		//inicializa o systray
		mainSysTray = new MainSystemTray();
		
		//inicializa o border pane e seta o background
		BorderPane borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-image: url('"+Resources.BG_LINK+"');");
		
		
		
		TextArea txtArea = new TextArea();

		borderPane.setTop(startHbox());
		
		Scene scene = new Scene(borderPane);
		
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			mainStage.close();
		});
		
		mainStage.initStyle(StageStyle.UNDECORATED);
		mainStage.setScene(scene);
		mainStage.setWidth(Resources.BG_WIDTH);
		mainStage.setHeight(Resources.BG_HEIGHT);
		mainStage.setResizable(false);
		
		initialized = true;
	}
	
	private HBox startHbox(){
		HBox topBox = new HBox();
		//topBox.setStyle("-fx-background-color: rgb(0,0,0,0.0);");
		
		StackPane leftTopBox = new StackPane();
		StackPane rightTopBox = new StackPane();
		
		ImageView moveImage = new ImageView(Resources.MOVEIMAGE);
		ImageView closeImage = new ImageView(Resources.CLOSEIMAGE);
		
		closeImage.setOnMouseClicked((event) ->{
			mainStage.close();
		});
		
		moveImage.setOnMouseDragged((event) ->{
			mainStage.setX(event.getScreenX());
			mainStage.setY(event.getScreenY());
		});
		
		leftTopBox.getChildren().add(moveImage);
		leftTopBox.setAlignment(Pos.CENTER_LEFT);
		topBox.getChildren().add(leftTopBox);
		
		rightTopBox.getChildren().add(closeImage);
		rightTopBox.setAlignment(Pos.CENTER_RIGHT);
		topBox.getChildren().add(rightTopBox);
		topBox.setHgrow(rightTopBox, Priority.ALWAYS);
		
		return topBox;
	}
	
	
	public static boolean isInitialized() {
		return initialized;
	}
	
	public static void openNote(){
		if(!isInitialized()){
			mainSysTray.displayNotification("Ainda não inicializado!", "Aguarde só mais um instantinho...", MessageType.INFO);
			return;
		}
		Platform.runLater(() -> mainStage.show());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Mostra alertas no modelo padrão do JavaFX
	 * @param message A mensagem do alerta
	 * @param title O titulo do alerta
	 * @param type O tipo de alerta (Constantes da enum AlertType)
	 * @return true se o tipo de alerta for de confirmação e o usuario pressionar OK; false nos demais casos.
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
