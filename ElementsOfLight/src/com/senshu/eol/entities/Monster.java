package com.senshu.eol.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Monster extends Sprite {

	protected int health;
	protected boolean visible = true;
	
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
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public abstract void update(float delta);
	
	public abstract Rectangle getCollisionBox();

}
