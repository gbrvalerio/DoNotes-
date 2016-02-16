package br.com.gabrielvalerio.DoNotes.core;

import java.awt.TrayIcon.MessageType;
import java.util.concurrent.ExecutionException;
import br.com.gabrielvalerio.DoNotes.resources.Resources;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
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
		
		ImageView sidebar = new ImageView();
		sidebar.setImage(Resources.SIDEBARCLOSED);
		sidebar.setOnMouseClicked((event) ->{
			if(event.getButton() == MouseButton.PRIMARY){
				if(sidebar.getImage() == Resources.SIDEBARCLOSED)
					sidebar.setImage(Resources.SIDEBAROPEN);
				else
					sidebar.setImage(Resources.SIDEBARCLOSED);
			}	
		});
		
		
		TextArea textArea = new TextArea();
		textArea.setFont(Resources.FONT);
		//tira o scroll pro lado
		textArea.setWrapText(true);
		
		borderPane.setCenter(textArea);
		borderPane.setTop(startTopBar());
		borderPane.setMinHeight(Resources.BG_WIDTH);
		
		HBox root = new HBox();
		root.getChildren().addAll(borderPane, sidebar);
		root.setMaxWidth(Resources.BG_WIDTH + Resources.SIDEBARWIDTH);
		root.setPadding(new Insets(0));
		HBox.setHgrow(borderPane, Priority.ALWAYS);
		
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(Resources.TRANSPTXTAREA);
		
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			mainStage.close();
		});
		
		mainStage.initStyle(StageStyle.UNDECORATED);
		mainStage.setScene(scene);
		mainStage.setWidth(Resources.BG_WIDTH + Resources.SIDEBARWIDTH);
		mainStage.setHeight(Resources.BG_HEIGHT);
		mainStage.setResizable(false);
		
		initialized = true;
	}
	
	private HBox startTopBar(){
		HBox topBox = new HBox();
		//topBox.setStyle("-fx-background-color: rgb(0,0,0,0.0);");
		
		StackPane leftTopBox = new StackPane();
		StackPane rightTopBox = new StackPane();
		
		rightTopBox.setPadding(new Insets(0, Resources.SIDEBARWIDTH+4, 0, 0));
		
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
	
	/**
	 * Verifica se o objeto já foi criado
	 * @return true se já há uma instancia e false caso contrário
	 */
	public static boolean isInitialized() {
		return initialized;
	}
	
	/**
	 * Chamar esse método abre a tela do stikynote.
	 */
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
