package stg;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.stage.*;

import stg.app.*;

/**
 * エントリーポイント。
 * JavaFXからウィンドウの情報を受け取り・設定し、アプリ(App)を開始する。
 */
public class Main extends Application {
    private App app;

    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 800);
        Canvas canvas = new Canvas(600, 800);
        GraphicsContext g = canvas.getGraphicsContext2D();
        stage.setTitle("Sample STG");
        stage.setResizable(false);
        stage.setScene(scene);
        root.getChildren().add(canvas);
        stage.show();

        this.app = new App(scene, g);
        this.app.start();
    }
}
