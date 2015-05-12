package de.donmatheo.games.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.utils.UBJsonReader;
import de.donmatheo.games.components.*;

/**
 * Created by donmatheo on 04.05.2015.
 */
public class Ship extends Entity {
    public Ship() {
        VelocityComponent velocity = new VelocityComponent();
        ModelComponent model = new ModelComponent();
        velocity.velocity = 20;

        UBJsonReader jsonReader = new UBJsonReader();
        ModelLoader loader = new G3dModelLoader(jsonReader);
        model.model = loader.loadModel(Gdx.files.internal("data/folded_paper.g3db"));
        model.instance = new ModelInstance(model.model);
        model.instance.transform.translate(2,0,2);

        //refactor to component
        model.animation = new AnimationController(model.instance);

        add(velocity).add(new RenderComponent()).add(model).add(new PlayerComponent());
    }
}
