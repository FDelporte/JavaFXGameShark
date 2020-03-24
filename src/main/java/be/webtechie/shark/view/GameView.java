package be.webtechie.shark.view;

import be.webtechie.shark.elements.BitingShark;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GameView extends Pane {

    /**
     * Drawing on Canvas http://zetcode.com/gui/javafx/canvas/
     * <p>
     * Canvas animation https://mkyong.com/javafx/javafx-animated-ball-example/
     */

    private final int width;
    private final int height;

    private final Random random = new Random();

    private final List<MovingDot> dots;
    private final Slider slider;

    public GameView(int width, int height) {
        this.width = width;
        this.height = height;
        this.dots = new ArrayList<>();
        this.slider = new Slider(10, 500, 50);
        this.slider.setMinWidth(250);
        this.slider.setOrientation(Orientation.HORIZONTAL);
        this.slider.setBlockIncrement(10);
        this.slider.setMajorTickUnit(10);
        this.slider.setMinorTickCount(0);
        this.slider.setShowTickLabels(true);
        this.slider.setSnapToTicks(true);
        this.slider.valueProperty().addListener((observable, oldValue, newValue) -> this.createUI());

        this.createUI();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), this::timelineEvent));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void createUI() {
        this.getChildren().clear();
        this.getChildren().removeAll();
        this.dots.clear();
        for (int i = 0; i < Math.round(this.slider.getValue()); i++) {
            MovingDot dot = new MovingDot(
                    getRandomNumberInRange(1, 3),
                    getRandomNumberInRange(1, 3),
                    getRandomNumberInRange(0, width),
                    getRandomNumberInRange(0, height));
            this.dots.add(dot);
            this.getChildren().add(dot);
        }

        this.getChildren().add(new BitingShark());
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return this.random.nextInt((max - min) + 1) + min;
    }

    private void timelineEvent(ActionEvent e) {
        Bounds bounds = this.getBoundsInLocal();

        for (MovingDot dot : this.dots) {
            dot.move(bounds);
        }
    }
}
