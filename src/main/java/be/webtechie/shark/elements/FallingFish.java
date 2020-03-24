package be.webtechie.shark.elements;

import java.util.Arrays;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * https://stackoverflow.com/questions/47876381/javafx-and-sprite-animation-how-do-i-make-an-animation-cycle-to-change-pictures
 */
public class FallingFish {

    private final static Image FISH_1 =  new Image(BitingShark.class.getResource("/images/fish1.png").toString());
    private final ImageView imageView;
    private final List<Image> images;
    private int imageIndex = 0 ;
    private final int frameTimeMillis = 150;

    public FallingFish() {
        this.imageView = new ImageView();
        this.images = Arrays.asList(
            FISH_1
        );

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(frameTimeMillis),
                e -> imageView.setImage(images.get(imageIndex++))));

        timeline.setCycleCount(images.size());
        timeline.play();
    }
}
