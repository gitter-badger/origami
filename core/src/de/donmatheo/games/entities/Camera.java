package de.donmatheo.games.entities;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import de.donmatheo.games.components.CameraComponent;
import de.donmatheo.games.systems.RenderingSystem;

/**
 * Created by donmatheo on 04.05.2015.
 */
public class Camera extends Entity {

    public Camera(Engine engine, Ship target) {
        CameraComponent camera = new CameraComponent();
        camera.camera = engine.getSystem(RenderingSystem.class).getCamera();
        camera.target = target;
        add(camera);
    }
}
