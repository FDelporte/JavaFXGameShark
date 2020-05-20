package be.webtechie.shark;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;
import static com.almasb.fxgl.dsl.FXGL.getGameController;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGL.onKey;
import static com.almasb.fxgl.dsl.FXGL.run;
import static com.almasb.fxgl.dsl.FXGL.showMessage;
import static com.almasb.fxgl.dsl.FXGL.spawn;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameScene;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getGameState;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getUIFactory;

import be.webtechie.shark.SharkGameFactory.EntityType;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import java.util.Map;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Main class of the application
 */
public class SharkGameApp extends GameApplication {

    /**
     * Reference to the factory which will defines how all the types must be created.
     */
    private final SharkGameFactory sharkGameFactory = new SharkGameFactory();

    /**
     * Player object we are going to use to provide to the factory so it can start a bullet from the player center.
     */
    private Entity player;

    /**
     * Main entry point where the application starts.
     *
     * @param args Start-up arguments
     */
    public static void main(String[] args) {
        // Launch the FXGL game application
        launch(args);
    }

    /**
     * General game settings. For now only the title is set, but a longer list of options is available.
     *
     * @param settings The settings of the game which can be further extended here.
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("Viks Shark Game");
    }

    /**
     * General game variables. Used to hold the points and lives.
     *
     * @param vars The variables of the game which can be further extended here.
     */
    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score", 0);
        vars.put("lives", 5);
    }

    @Override
    protected void initUI() {
        Text scoreLabel = getUIFactory().newText("Score", Color.BLACK, 22);
        Text scoreValue = getUIFactory().newText("", Color.BLACK, 22);
        Text livesLabel = getUIFactory().newText("Lives", Color.BLACK, 22);
        Text livesValue = getUIFactory().newText("", Color.BLACK, 22);

        scoreLabel.setTranslateX(20);
        scoreLabel.setTranslateY(20);

        scoreValue.setTranslateX(90);
        scoreValue.setTranslateY(20);

        livesLabel.setTranslateX(getAppWidth() - 100);
        livesLabel.setTranslateY(20);

        livesValue.setTranslateX(getAppWidth() - 30);
        livesValue.setTranslateY(20);

        scoreValue.textProperty().bind(getGameState().intProperty("score").asString());
        livesValue.textProperty().bind(getGameState().intProperty("lives").asString());

        getGameScene().addUINodes(scoreLabel, scoreValue, livesLabel, livesValue);
    }

    /**
     * Input configuration, here you configure all the input events like key presses, mouse clicks, etc.
     */
    @Override
    protected void initInput() {
        onKey(KeyCode.LEFT, () -> this.player.translateX(-5));
        onKey(KeyCode.RIGHT, () -> this.player.translateX(5));
    }

    /**
     * Initialization of the game by providing the {@link EntityFactory}.
     */
    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(this.sharkGameFactory);

        // Add the player
        this.player = spawn("shark", getAppWidth() / 2 - 15, getAppHeight() - 15);

        // Add a new enemy every second
        run(() -> spawn("fish", getAppWidth() / 2, getAppHeight() / 2), Duration.seconds(1.0));
    }

    @Override
    protected void onUpdate(double tpf) {
        // For each entity of Type.FISH translate (move) it down
        for (Entity entity : getGameWorld().getEntitiesByType(EntityType.FISH)) {
            entity.translateY(150 * tpf);

            if (entity.getY() >= getAppHeight()) {
                getGameState().intProperty("lives").set(getGameState().intProperty("lives").get() - 1);
                entity.removeFromWorld();

                if (getGameState().intProperty("lives").get() <= 0) {
                    showMessage("Game Over!", () -> {
                        getGameController().startNewGame();
                    });
                }
            }
        }
    }

    /**
     * Initialization of the physics to detect e.g. collisions.
     */
    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.FISH, EntityType.SHARK, (fish, shark) -> {
            fish.removeFromWorld();
            getGameState().intProperty("score").set(getGameState().intProperty("score").get() + 1);
        });
    }
}