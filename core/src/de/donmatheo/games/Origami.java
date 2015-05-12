package de.donmatheo.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Origami extends Game {

	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}

	@Override
	public void render() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		super.render();
	}
}
