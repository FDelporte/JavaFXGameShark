package be.webtechie.shark;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;

import be.webtechie.shark.elements.FishSkin;
import be.webtechie.shark.elements.SharkSkin;
import be.webtechie.shark.util.RandomHelper;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * The factory which defines how each entity looks like
 */
public class SharkGameFactory implements EntityFactory {

    /**
     * Types of objects we are going to use in our game.
     */
    public enum EntityType {
        SHARK, FISH
    }

    @Spawns("shark")
    public Entity newShark(SpawnData data) {
        SharkSkin skin = SharkSkin.SHARK;
        var channel = new AnimationChannel(skin.getImages(), Duration.millis(250));

        return entityBuilder()
                .from(data)
                .type(EntityType.SHARK)
                .viewWithBBox(new AnimatedTexture(channel).loop())
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("fish")
    public Entity newFish(SpawnData data) {
        FishSkin skin = FishSkin.getRandom();

        var channel = new AnimationChannel(skin.getImages(), Duration.millis(250));

        return entityBuilder()
                .from(data)
                .type(EntityType.FISH)
                .viewWithBBox(new AnimatedTexture(channel).loop())
                .at(RandomHelper.getRandomNumberInRange(5, getAppWidth() - 15), 0)
                .with(new CollidableComponent(true))
                .build();
    }
}
