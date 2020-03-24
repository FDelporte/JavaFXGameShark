package be.webtechie.shark;

import be.webtechie.shark.view.GameView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

// https://www.codeproject.com/tips/788527/creating-animation-from-sequence-of-images-in-java
public class SharkGameApp extends Application {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    private GameView view;

    @Override
    public void init() {
        view = new GameView(WIDTH, HEIGHT);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(view, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Moving dots framerate checker");
        stage.show();
    }

    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}