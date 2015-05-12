package de.donmatheo.games.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import de.donmatheo.games.entities.Ship;

/**
 * Created by donmatheo on 04.05.2015.
 */
public class CameraComponent extends Component {
    public Ship target;
    public PerspectiveCamera camera;
}
