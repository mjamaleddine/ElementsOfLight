package com.senshu.eol.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {

	/** the movement velocity */
	private Vector2 velocity = new Vector2();
	
	private float speed = 60 * 2, gravity = 6 * 1.8f, animationTime = 0;
	
	private Animation still, left, right, up , down;
	private TiledMapTileLayer collisionLayer;
	
	private float _x = getX(), _y = getY(), pwidth = getWidth(), pheight = getHeight();
	
	private String blockedKey = "blocked";
	
	public Player(Animation still, Animation left, Animation right,
			Animation up, Animation down, TiledMapTileLayer collisionLayer){
		super(still.getKeyFrame(0));
		this.still = still;
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.collisionLayer = collisionLayer;
	}
	
	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	public void update(float delta){
		
		//save old position
		float oldX = getX(), oldY = getY();
		boolean collisionX = false, collisionY = false;
		
		//move on x
		setX(getX() + velocity.x * delta);
		
		_x = getX() + 8; _y = getY(); pwidth = 16; pheight = 16;
		if(velocity.x < 0) // going left
			collisionX = collidesLeft();
		else if(velocity.x > 0) // going right
			collisionX = collidesRight();

		//react to x collision
		if(collisionX){
			setX(oldX);
			velocity.x = 0;
		}
		
		
		//move on y
		setY(getY() + velocity.y * delta);
	
		if(velocity.y < 0){ // going down
			_y = getY();
			collisionY = collidesBottom();
			}
		else if(velocity.y > 0){ // going up
			_y = getY() + 12;
			collisionY = collidesTop();
			}
		
		//react to y collision
		if(collisionY){
			setY(oldY);
			velocity.y = 0;
		}

		// update animation
		animationTime += delta;
		setRegion(velocity.x < 0 ? left.getKeyFrame(animationTime) : velocity.x > 0 ? right.getKeyFrame(animationTime) : velocity.y > 0 ? up.getKeyFrame(animationTime) : velocity.y < 0 ? down.getKeyFrame(animationTime) : still.getKeyFrame(animationTime));
		if(velocity.x == 0 && velocity.y == 0) animationTime = 0;

	}

	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
	}
/*
	public boolean collidesRight() {
		for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
			if(isCellBlocked(getX() + getWidth() , getY() + step))
				return true;
		return false;
	}

	public boolean collidesLeft() {
		for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
			if(isCellBlocked(getX(), getY() + step))
				return true;
		return false;
	}

	public boolean collidesTop() {
		for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(getX() + step, getY() + getHeight()))
				return true;
		return false;
	}

	public boolean collidesBottom() {
		for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(getX() + step, getY()))
				return true;
		return false;
	}*/
	
	public boolean collidesRight() {
		for(float step = 0; step < pheight; step += collisionLayer.getTileHeight() / 2)
			if(isCellBlocked(_x + pwidth , _y + step)){
				System.out.println("Rechts Collision");
				return true;}	
		return false;
	}

	public boolean collidesLeft() {
		for(float step = 0; step < pheight; step += collisionLayer.getTileHeight() / 2)
			if(isCellBlocked(_x, _y + step)){
				System.out.println("Links Collision");
				return true;}
		return false;
	}

	public boolean collidesTop() {
		for(float step = 0; step < pwidth; step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(_x + step, _y + pheight)){
				System.out.println("Oben Collision");
				return true;}
		return false;
	}

	public boolean collidesBottom() {
		for(float step = 0; step < pwidth; step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(_x + step, _y)){
				System.out.println("Unten Collision");
				return true;}
		return false;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
}
