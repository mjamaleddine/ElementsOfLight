package com.senshu.eol.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SlimeMonster extends Monster {

	private float animationTime, startPointX = 0;

	private Animation left, right;
	
	private boolean turn = false, visible = true;

	public SlimeMonster(int health, Animation left, Animation right) {
		super(health, left, right);
		this.left = left;
		this.right = right;
	}

	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	@Override
	public void update(float delta) {
		if(startPointX == 0){
			startPointX = getX();
		}
		
		animationTime += delta;
		
		//basic patrolling
		if(turn == false && getX()>startPointX-80){
			setRegion(left.getKeyFrame(animationTime));
			setX(getX()-0.2f);
		}else{
			turn = true;
		}
		
		if(turn == true && getX()< startPointX){
			setRegion(right.getKeyFrame(animationTime));
			setX(getX()+0.2f);
		}else{
			turn = false;
		}
		
		//check monster health
		if(this.getHealth() < 0){
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 0);
			visible = false;
		}
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
