package br.com.gabrielvalerio.DoNotes.core;

import java.awt.TrayIcon.MessageType;
import java.util.concurrent.ExecutionException;
import br.com.gabrielvalerio.DoNotes.resources.Resources;
import br.com.gabrielvalerio.DoNotes.util.NoteFinder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	//main variables
	private static MainSystemTray 	mainSysTray;
	private static Stage			mainStage;
	private static boolean			initialized = false;
	
	private Scene 				scene;
	private HBox 				root;
	private BorderPane 			borderPane;
	private ImageView 			sidebar;
	private TextArea 			textArea;
	private HBox 				topBox;
	private StackPane 			leftTopBox;
	private StackPane 			rightTopBox;
	private ImageView 			moveImage;
	private ImageView			closeImage;
	private ListView<String>	noteList;
	
	private boolean				smallSize = false;
	private boolean				sidebarOpen = false;
	
	private void changeSize(){
		if(isSmallSize()){
			//setting big
			borderPane.setStyle("-fx-background-image: url('"+Resources.BG_LINK+"');");
			noteList.setStyle("-fx-background-image: url('"+Resources.LISTBG_LINK+"');");
			sidebar.setImage((isSidebarOpen() ? Resources.SIDEBARCLOSED : Resources.SIDEBAROPEN));
			noteList.setMinHeight(Resources.BG_HEIGHT);
			borderPane.setMinHeight(Resources.BG_WIDTH);
			root.setMaxWidth(Resources.BG_WIDTH + Resources.SIDEBARWIDTH);
			mainStage.setWidth(Resources.BG_WIDTH + Resources.SIDEBARWIDTH);
			mainStage.setHeight(Resources.BG_HEIGHT);
			
			noteList.autosize();
			smallSize = false;
		} else{
			//setting small
			borderPane.setStyle("-fx-background-image: url('"+Resources.SMALLBG_LINK+"');");
			noteList.setStyle("-fx-background-image: url('"+Resources.SMALLLISTBG_LINK+"');");
			sidebar.setImage((isSidebarOpen() ? Resources.SMALLSIDEBARCLOSED : Resources.SMALLSIDEBAROPEN));
			noteList.setMinHeight(Resources.SMALLBG_HEIGHT);
			borderPane.setMinHeight(Resources.SMALLBG_WIDTH);
			root.setMaxWidth(Resources.SMALLBG_WIDTH + Resources.SIDEBARWIDTH);
			mainStage.setWidth(Resources.SMALLBG_WIDTH + Resources.SIDEBARWIDTH);
			mainStage.setHeight(Resources.SMALLBG_HEIGHT);
			
			noteList.autosize();
			smallSize = true;
		}
	}
	
	public boolean isSmallSize() {
		return smallSize;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root 			= new HBox();
		borderPane 		= new BorderPane();
		sidebar 		= new ImageView();
		textArea 		= new TextArea();
		noteList		= NoteFinder.getNotesList();
		
		mainStage = primaryStage;
		mainSysTray = new MainSystemTray();
		
		//evita a thread do fx de fechar caso nao tenha nenhuma janela ainda
		Platform.setImplicitExit(false);
		
		//seta o background
		borderPane.setStyle("-fx-background-image: url('"+Resources.BG_LINK+"');");
		
		sidebar.setImage(Resources.SIDEBARCLOSED);
		
		StackPane noteListPane = new StackPane();
		noteListPane.setAlignment(Pos.CENTER_RIGHT);
		noteListPane.getChildren().add(noteList);
		noteListPane.setMaxWidth(Resources.NOTELISTWIDTH);
		HBox.setHgrow(noteListPane, Priority.ALWAYS);
		
		noteList.setMaxWidth(Resources.NOTELISTWIDTH);
		noteList.setMinHeight(Resources.BG_HEIGHT);
		noteList.setStyle("-fx-background-image: url('"+Resources.LISTBG_LINK+"');");
		
		//ações de abrir/fechar a sidebar
		sidebar.setOnMouseClicked((event) ->{
			if(event.getButton() == MouseButton.PRIMARY){
				if(!isSidebarOpen()){
					sidebar.setImage((isSmallSize() ? Resources.SMALLSIDEBAROPEN :Resources.SIDEBAROPEN));
					mainStage.setWidth(mainStage.getWidth() + Resources.NOTELISTWIDTH);
					setSidebarOpen(true);
					root.getChildren().add(noteListPane);
				} else{
					sidebar.setImage((isSmallSize() ? Resources.SMALLSIDEBARCLOSED :Resources.SIDEBARCLOSED));
					root.getChildren().remove(noteListPane);
					mainStage.setWidth(mainStage.getWidth() - Resources.NOTELISTWIDTH);
					setSidebarOpen(false);
				}
			}	
		});
		
		textArea.setFont(Resources.FONT);
		//tira o scroll pro lado
		textArea.setWrapText(true);
		
		borderPane.setCenter(textArea);
		borderPane.setTop(startTopBar());
		borderPane.setMinHeight(Resources.BG_WIDTH);
		
		
		root.getChildren().addAll(borderPane, sidebar);
		root.setMaxWidth(Resources.BG_WIDTH + Resources.SIDEBARWIDTH);
		root.setPadding(new Insets(0));
		HBox.setHgrow(borderPane, Priority.ALWAYS);
		
		
		scene = new Scene(root);
		scene.getStylesheets().addAll(Resources.CSSTXTAR_LINK, Resources.CSSTXTVW_LINK);
		
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			changeSize();
		});
		
		mainStage.initStyle(StageStyle.UNDECORATED);
		mainStage.setScene(scene);
		mainStage.setWidth(Resources.BG_WIDTH + Resources.SIDEBARWIDTH);
		mainStage.setHeight(Resources.BG_HEIGHT);
		mainStage.setResizable(false);
		
		
		
		initialized = true;
		mainSysTray.displayNotification("Pronto!", "Para adicionar uma nova nota clique no ícone com o botão direito!", MessageType.INFO);
	}
	
	public void setSidebarOpen(boolean sidebarOpen) {
		this.sidebarOpen = sidebarOpen;
	}
	
	public boolean isSidebarOpen() {
		return sidebarOpen;
	}
	
	private HBox startTopBar(){
		topBox = new HBox();
		//topBox.setStyle("-fx-background-color: rgb(0,0,0,0.0);");
		
		leftTopBox = new StackPane();
		rightTopBox = new StackPane();
		
		moveImage = new ImageView(Resources.MOVEIMAGE);
		closeImage = new ImageView(Resources.CLOSEIMAGE);
		
		closeImage.setOnMouseClicked((event) ->{
			//mainStage.close();
			changeSize();
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
		HBox.setHgrow(rightTopBox, Priority.ALWAYS);
		
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
