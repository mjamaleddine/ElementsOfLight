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

public class SlimeMonster extends Monster {

	private float animationTime, startPointX = 0, startPointY = 0;

	private Animation left, right;
	
	private boolean turn = false;
		
	private ShapeRenderer healthBar;

	private OrthographicCamera camera;

	private Player player;
	
	private Rectangle collisionBox;

	public SlimeMonster(int health, Animation left, Animation right, OrthographicCamera camera, Player player) {
		super(health, left, right);
		this.left = left;
		this.right = right;
		this.camera = camera;
		this.player = player;
		
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
		if(this.health < 20f){
			healthBar.begin(ShapeType.Filled);
			healthBar.setColor((1-(this.health/20f))*2, (this.health/20f), 0, 1);
			healthBar.rect(collisionBox.getX()-5, collisionBox.getY()+15, (this.health/20f)*30f, 5);
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
		animationTime += delta;
		
		if(startPointY == 0 && startPointX == 0){
			startPointY = getY();
			startPointX = getX();
		}
		
		//update AI
		updateAI();
		
		//update CollisionBox
		collisionBox.set((this.getX()+5), (this.getY()+9), 20, 15);
		
		//check monster health
		if(this.health <= 0){
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 0);
			this.visible = false;
			this.health = 0;
			this.setX(startPointX);
			this.setY(startPointY);
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
	
	private void updateAI(){
		//basic patrolling
//		if(turn == false && getX()>startPointX-80){
//			setRegion(left.getKeyFrame(animationTime));
//			setX(getX()-0.2f);
//		}else{
//			turn = true;
//		}
//		
//		if(turn == true && getX()< startPointX){
//			setRegion(right.getKeyFrame(animationTime));
//			setX(getX()+0.2f);
//		}else{
//			turn = false;
//		}
		
		//basic player finding algorithm if player is on the screen
		if(camera.frustum.pointInFrustum(new Vector3(this.getX(), this.getY(), 0))) {
			if(player.getX() > this.getX()){
				setRegion(right.getKeyFrame(animationTime));
				setX(getX()+0.4f);
			}else{
				setRegion(left.getKeyFrame(animationTime));
				setX(getX()-0.4f);
			}
			if(player.getY() > this.getY()){
				setY(getY()+0.4f);
			}else{
				setY(getY()-0.4f);
			}
		}else{
			this.setX(startPointX);
			this.setY(startPointY);
		}
	}
}
