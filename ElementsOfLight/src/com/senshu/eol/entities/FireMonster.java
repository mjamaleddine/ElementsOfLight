package com.senshu.eol.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.senshu.eol.EolGame;

public class FireMonster extends Monster {

	private float animationTime, startPointY = 0;

	private Animation up, down;
	
	private boolean turn = false;
	
	private ShapeRenderer healthBar;

	private OrthographicCamera camera;

	private Rectangle collisionBox;


	public FireMonster(int health, Animation up, Animation down, OrthographicCamera camera) {
		super(health, up, down);
		this.up = up;
		this.down = down;
		this.camera = camera;
		collisionBox = new Rectangle();
		healthBar = new ShapeRenderer();
	}

	private void healthBarinit() {
		healthBar.setProjectionMatrix(camera.combined);
		if (EolGame.DEBUG) {
			healthBar.begin(ShapeType.Line);
			healthBar.setColor(0, 1, 0, 0.5f);
			healthBar.rect(collisionBox.getX(), collisionBox.getY(),
					collisionBox.getWidth(), collisionBox.getHeight());
			healthBar.end();
		}
		if(this.health < 40f){
			healthBar.begin(ShapeType.Filled);
			healthBar.setColor((1-(this.health/20f))*2, (this.health/20f), 0, 1);
			healthBar.rect(collisionBox.getX()+6, collisionBox.getY()+45, (this.health/40f)*50f, 5);
			healthBar.end();
		}
	}
	
	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
		spriteBatch.end();
		healthBarinit();
		spriteBatch.begin();
	}
	
	@Override
	public void update(float delta) {
		//update CollisionBox
		collisionBox.set(this.getX(), (this.getY()+9), 62, 45);
				
		if(startPointY == 0){
			startPointY = getY();
		}
		
		animationTime += delta;
		
		//basic patrolling
		if(turn == false && getY()>startPointY-160){
			setRegion(up.getKeyFrame(animationTime));
			setY(getY()-0.6f);
		}else{
			turn = true;
		}
		
		if(turn == true && getY()< startPointY){
			setRegion(down.getKeyFrame(animationTime));
			setY(getY()+0.6f);
		}else{
			turn = false;
		}
		
		//check monster health
		if(this.health <= 0){
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 0);
			this.visible = false;
			this.health = 0;
			//checks if dead Monster is not on camera
			if(!camera.frustum.pointInFrustum(new Vector3(this.getX(), this.getY(), 0))) {
				this.health = 20;
			}
		}else{
			//respawn monster
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
			this.visible = true;
		}
	}
	
	@Override
	public Rectangle getCollisionBox() {
		return collisionBox;
	}

}
