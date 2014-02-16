package com.senshu.eol.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TreeMonster extends Monster{

	private float animationTime, startPointY = 0;

	private Animation up, down;
	
	private boolean turn = false, visible = true;

	public TreeMonster(int health, Animation up, Animation down) {
		super(health,up,down);
		this.up = up;
		this.down = down;
	}

	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	public void update(float delta) {
		if(startPointY == 0){
			startPointY = getY();
		}
		
		animationTime += delta;
		
		//basic patrolling
		if(turn == false && getY()>startPointY-160){
			setRegion(down.getKeyFrame(animationTime));
			setY(getY()-0.2f);
		}else{
			turn = true;
		}
		
		if(turn == true && getY()< startPointY){
			setRegion(up.getKeyFrame(animationTime));
			setY(getY()+0.2f);
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
