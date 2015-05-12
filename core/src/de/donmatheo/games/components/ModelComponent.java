package de.donmatheo.games.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;

/**
 * Created by donmatheo on 04.05.2015.
 */
public class ModelComponent extends Component{

    public Model model = null;
    public ModelInstance instance= null;
    public AnimationController animation;
}
