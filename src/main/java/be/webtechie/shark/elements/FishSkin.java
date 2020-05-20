package be.webtechie.shark.elements;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;

public enum FishSkin {
    BLUE(Arrays.asList(
            new Image(FishSkin.class.getResource("/fish/blue1.png").toString(), 22, 55, true, true),
            new Image(FishSkin.class.getResource("/fish/blue2.png").toString(), 22, 55, true, true),
            new Image(FishSkin.class.getResource("/fish/blue3.png").toString(), 22, 55, true, true))),
    RED(Arrays.asList(
            new Image(FishSkin.class.getResource("/fish/red1.png").toString(), 20, 40, true, true))),
    GREY(Arrays.asList(
            new Image(FishSkin.class.getResource("/fish/grey1.png").toString(), 22, 45, true, true))),
    DARK_GREY(Arrays.asList(
            new Image(FishSkin.class.getResource("/fish/darkgrey1.png").toString(), 20, 45, true, true)));

    private final List<Image> images;

    FishSkin(List<Image> images) {

        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public static FishSkin getRandom() {
        Random random = new Random();
        return Collections.unmodifiableList(Arrays.asList(values())).get(random.nextInt(values().length));
    }
}
