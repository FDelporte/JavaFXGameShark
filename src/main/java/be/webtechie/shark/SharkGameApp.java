package be.webtechie.shark;

import be.webtechie.shark.view.GameView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

// https://www.codeproject.com/tips/788527/creating-animation-from-sequence-of-images-in-java
public class SharkGameApp extends Application {

    private static final int WIDTH = 640;
    private static final int HEIGHT = 900;

    private GameView view;

    @Override
    public void init() {
        view = new GameView(WIDTH, HEIGHT);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(view, WIDTH, HEIGHT);
        scene.setOnKeyPressed(this::handleKeyPress);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Viks Shark Game");
        stage.show();
    }

    private void handleKeyPress(KeyEvent e) {
        this.view.handleKeyPress(e);
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