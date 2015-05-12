package de.donmatheo.games.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import de.donmatheo.games.components.ModelComponent;
import de.donmatheo.games.components.PlayerComponent;
import de.donmatheo.games.components.VelocityComponent;

/**
 * Created by donmatheo on 03.05.2015.
 */
public class MovementSystem extends IteratingSystem {

    private ComponentMapper<VelocityComponent> velocityM;
    private ComponentMapper<ModelComponent> modelM;

    public MovementSystem() {
        super(Family.all(VelocityComponent.class, ModelComponent.class, PlayerComponent.class).get());

        velocityM = ComponentMapper.getFor(VelocityComponent.class);
        modelM = ComponentMapper.getFor(ModelComponent.class);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent vel = velocityM.get(entity);
        ModelComponent model = modelM.get(entity);

        if(Gdx.input.isKeyPressed(Input.Keys.D))
            model.instance.transform.translate(vel.velocity *deltaTime,0,0);

        if(Gdx.input.isKeyPressed(Input.Keys.A))
            model.instance.transform.translate(-vel.velocity *deltaTime,0,0);

        if(Gdx.input.isKeyPressed(Input.Keys.W))
            model.instance.transform.translate(0, 0, vel.velocity *deltaTime);

        if(Gdx.input.isKeyPressed(Input.Keys.S))
            model.instance.transform.translate(0,0, -vel.velocity *deltaTime);

    }
}
