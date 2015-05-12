package de.donmatheo.games;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ScreenAdapter;
import de.donmatheo.games.entities.Camera;
import de.donmatheo.games.entities.Ship;
import de.donmatheo.games.systems.CameraMovementSystem;
import de.donmatheo.games.systems.MovementSystem;
import de.donmatheo.games.systems.RenderingSystem;

/**
 * Created by donmatheo on 03.05.2015.
 */
public class GameScreen extends ScreenAdapter {

    private Engine engine;

    public GameScreen(Origami game) {
        engine = new Engine();
        engine.addSystem(new MovementSystem());
        engine.addSystem(new RenderingSystem());
        engine.addSystem(new CameraMovementSystem());
        Ship ship = new Ship();
        engine.addEntity(ship);
        engine.addEntity(new Camera(engine, ship));
    }

    @Override
    public void render(float delta) {
        update(delta);
    }

    private void update(float delta) {
        engine.update(delta);
    }
}
