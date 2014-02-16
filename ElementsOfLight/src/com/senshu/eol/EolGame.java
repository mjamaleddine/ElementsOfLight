package com.senshu.eol;

import com.badlogic.gdx.Game;
import com.senshu.eol.screeens.Play;

public class EolGame extends Game {

	public static final String VERSION = "1.0.0 Alpha";
	
	@Override
	public void create() {		
		setScreen(new Play());
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
