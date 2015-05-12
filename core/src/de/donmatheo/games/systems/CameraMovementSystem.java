package de.donmatheo.games.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
import de.donmatheo.games.components.CameraComponent;
import de.donmatheo.games.components.ModelComponent;
import de.donmatheo.games.components.VelocityComponent;
import de.donmatheo.games.entities.Camera;

/**
 * Created by donmatheo on 03.05.2015.
 */
public class CameraMovementSystem extends IteratingSystem {

    private ComponentMapper<CameraComponent> cameraM;
    private ComponentMapper<ModelComponent> modelM;
    private Vector3 lookAt = new Vector3();

    public CameraMovementSystem() {
        super(Family.all(CameraComponent.class).get());
        cameraM = ComponentMapper.getFor(CameraComponent.class);
        modelM = ComponentMapper.getFor(ModelComponent.class);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CameraComponent cam = cameraM.get(entity);
        ModelComponent model = modelM.get(cam.target);
        model.instance.transform.getTranslation(lookAt);
        cam.camera.lookAt(lookAt);
    }
}
