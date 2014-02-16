package com.senshu.eol.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.senshu.eol.entities.Player;

public class KeyboardInput implements InputProcessor {

	Player player1;
	//Player player2;
	//Player player3;
	//Player player4;
	Vector2 velocity;
	
	public KeyboardInput(Player player1/*,Player player2,Player player3,Player player4*/){
		this.player1 = player1;
		//this.player2 = player2;
		//this.player3 = player3;
		//this.player4 = player4;
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
		case Keys.W:
			player1.moveUp(true);
			break;
		case Keys.A:
			player1.moveLeft(true);
			break;
		case Keys.D:
			player1.moveRight(true);
			break;
		case Keys.S:
			player1.moveDown(true);
			break;
		case Keys.R:
			player1.setSpeed(360);
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
		case Keys.W:
		case Keys.S:
			player1.moveDown(false);
			break;
		case Keys.A:
		case Keys.D:
			player1.moveRight(false);
			break;
		case Keys.R:
			player1.setSpeed(120);
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
