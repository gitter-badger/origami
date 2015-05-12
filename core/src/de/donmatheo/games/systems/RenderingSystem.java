package de.donmatheo.games.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;
import de.donmatheo.games.components.ModelComponent;
import de.donmatheo.games.components.RenderComponent;

/**
 * Created by donmatheo on 03.05.2015.
 */
public class RenderingSystem extends IteratingSystem {

    private final Model model;
    private final ModelInstance skycube;
    private ModelBatch modelBatch;
    private ComponentMapper<ModelComponent> modelM;
    private Array<Entity> renderQueue;

    private Environment environment;

    private PerspectiveCamera camera;

    public RenderingSystem() {
        super(Family.all(RenderComponent.class).get());
        modelM = ComponentMapper.getFor(ModelComponent.class);
        renderQueue = new Array<Entity>();
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(5f, 5f, 5f);
        camera.near = .1f;
        camera.far = 500f;
        modelBatch = new ModelBatch();


        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -0.2f, -0.1f, -0.2f));

        UBJsonReader jsonReader = new UBJsonReader();
        ModelLoader loader = new G3dModelLoader(jsonReader);
        model = loader.loadModel(Gdx.files.internal("data/cubemap.g3db"));
        skycube = new ModelInstance(model);
        skycube.transform.rotateRad(1,0,0, (float) Math.toRadians(180));
        // end of refactoring stuff
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera.update();

        modelBatch.begin(camera);
        modelBatch.render(skycube);

        for (Entity entity : renderQueue) {
            final ModelComponent model = modelM.get(entity);

            model.animation.update(deltaTime);
            if(Gdx.input.isTouched()) {
            model.animation.setAnimation("Armature|Fly", -1);
            }
            modelBatch.render(model.instance, environment);
        }
        modelBatch.end();

        renderQueue.clear();
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        renderQueue.add(entity);
    }

    public PerspectiveCamera getCamera() {
        return camera;
    }

}
