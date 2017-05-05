package com.theironyard.charlotte;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.theironyard.charlotte.screens.NewScreen;

public class ZombieDefenseGame extends Game {
	@Override
	public void create () {
	    setScreen(new NewScreen(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		screen.render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
	}
}
