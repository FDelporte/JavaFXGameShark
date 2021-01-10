package be.webtechie.shark.elements;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum SharkSkin {
    SHARK(Arrays.asList(
            new Image(SharkSkin.class.getResource("/shark/shark1.png").toString(), 40, 80, true, true)));

    private final List<Image> images;

    SharkSkin(List<Image> images) {

        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }
}
