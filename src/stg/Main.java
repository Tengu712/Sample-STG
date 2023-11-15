package stg;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import stg.app.*;

public class Main extends Application {
    private App app;

    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 800);
        Canvas canvas = new Canvas(600, 800);
        GraphicsContext g = canvas.getGraphicsContext2D();
        stage.setTitle("LiveCoding STG");
        stage.setResizable(false);
        stage.setScene(scene);
        root.getChildren().add(canvas);
        stage.show();

        this.app = new App(scene, g);
        this.app.start();
    }

    public void stop() {
        this.app.terminate();
    }
}
