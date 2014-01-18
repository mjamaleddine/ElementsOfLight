package com.senshu.eol.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.senshu.eol.entities.Player;

public class KeyboardInput implements InputProcessor {

	Player player;
	Vector2 velocity;
	
	public KeyboardInput(Player player){
		this.player = player;
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
		case Keys.W:
			velocity = player.getVelocity();
			velocity.y = player.getSpeed();
			player.setVelocity(velocity);
			break;
		case Keys.A:
			velocity = player.getVelocity();
			velocity.x = -player.getSpeed();
			player.setVelocity(velocity);
			break;
		case Keys.D:
			velocity = player.getVelocity();
			velocity.x = player.getSpeed();
			player.setVelocity(velocity);
			break;
		case Keys.S:
			velocity = player.getVelocity();
			velocity.y = -player.getSpeed();
			player.setVelocity(velocity);
			break;
		case Keys.R:
			player.setSpeed(360);
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
		case Keys.W:
		case Keys.S:
			velocity = player.getVelocity();
			velocity.y = 0;
			player.setVelocity(velocity);
			break;
		case Keys.A:
		case Keys.D:
			velocity = player.getVelocity();
			velocity.x = 0;
			player.setVelocity(velocity);
			break;
		case Keys.R:
			player.setSpeed(120);
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
