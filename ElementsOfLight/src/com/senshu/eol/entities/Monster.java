package com.senshu.eol.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Monster extends Sprite {

	private int health;
	
	public Monster(int health, Animation left, Animation right){
		super(left.getKeyFrame(0));
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public abstract void update(float delta);

}
