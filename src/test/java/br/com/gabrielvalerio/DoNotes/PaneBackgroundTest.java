package br.com.gabrielvalerio.DoNotes;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PaneBackgroundTest extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        VBox vbox = new VBox();
        root.setCenter(vbox);

        ToggleButton toggle = new ToggleButton("Toggle color");
        HBox controls = new HBox(5, toggle);
        controls.setAlignment(Pos.CENTER);
        root.setBottom(controls);

//        vbox.styleProperty().bind(Bindings.when(toggle.selectedProperty())
//                .then("-fx-background-color: cornflowerblue;")
//                .otherwise("-fx-background-color: white;"));

        vbox.backgroundProperty().bind(Bindings.when(toggle.selectedProperty())
                .then(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)))
                .otherwise(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))));

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}