package com.senshu.eol.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.senshu.eol.EolGame;

public class Player extends Sprite {

	public static int numOfPlayers = 0;
	public final int PLAYER;
	
	/** the movement velocity */
	private Vector2 velocity = new Vector2();
	
	private float speed = 50 * 2, animationTime = 0, oldTime, cdTime = 500000000;
	
	private Animation left, right, up , down;
	private TiledMapTileLayer collisionLayer;
	private TiledMapTileLayer foregroundLayer;
	
	private float _x = getX(), _y = getY(), pwidth = getWidth(), pheight = getHeight();
	
	private String blockedKey = "blocked";
	
	private int health = 6;

	private Sword sword;

	private int direction = 2;

	private Rectangle r1, r2;
	
	ArrayList<TreeMonster> treeMonArray;
	ArrayList<SlimeMonster> slimeMonArray;
	ArrayList<FireMonster> fireMonArray;
	
	private ShapeRenderer collisionDebug;

	private OrthographicCamera camera;
	
	private TextureAtlas heartHud;
	
	private Sprite heartHudSprite;

	
	public Player(Animation still, Animation left, Animation right,
			Animation up, Animation down, TiledMap map , Sword sword, 
				ArrayList<TreeMonster> treeMonArray, ArrayList<SlimeMonster> slimeMonArray, ArrayList<FireMonster> fireMonArray, OrthographicCamera camera){
		super(still.getKeyFrame(0));
		this.left = left;
		this.right = right;
		this.up = up;
		this.down = down;
		this.collisionLayer = (TiledMapTileLayer) map.getLayers().get("background");
		//unwichtig
		this.foregroundLayer = (TiledMapTileLayer) map.getLayers().get("backbackground");
		this.sword = sword;
		this.treeMonArray = treeMonArray;
		this.slimeMonArray = slimeMonArray;
		this.fireMonArray = fireMonArray;
		
		numOfPlayers ++;
		PLAYER = numOfPlayers;
				
		r1 = new Rectangle();
		r2 = new Rectangle();
		
		this.camera = camera;
		collisionDebug = new ShapeRenderer();
		
		//Initilize Heart Hud
		heartHud = new TextureAtlas("img/ui/heart_hud.pack");
		heartHudSprite = heartHud.createSprite("heart"+health);
	}
	
	private void healthBarinit() {
		collisionDebug.setProjectionMatrix(camera.combined);
		if (EolGame.DEBUG) {
			collisionDebug.begin(ShapeType.Line);
			collisionDebug.setColor(1, 0, 0, 0.5f);
			collisionDebug.rect(this.getX(), this.getY(), 32, 32);
			collisionDebug.end();
		}
	}
	
	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
		spriteBatch.end();
		healthBarinit();
		spriteBatch.begin();
		heartHudSprite.draw(spriteBatch);
	}
	
	public void update(float delta){
		//update Hud
		updateHeartBar();
		
		//save old position
		float oldX = getX(), oldY = getY();
		boolean collisionX = false, collisionY = false;
		
		//move on x
		setX(getX() + velocity.x * delta);
		
		_x = getX() + 8; _y = getY(); pwidth = 16; pheight = 16;
		if(velocity.x < 0){ // going left
			collisionX = collidesLeft();
			direction = 0;
		}else if(velocity.x > 0){ // going right
			collisionX = collidesRight();
			direction = 1;
		}

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
			direction = 2;
		}
		else if(velocity.y > 0){ // going up
			_y = getY() + 12;
			collisionY = collidesTop();
			direction = 3;
		}
		
		//react to y collision
		if(collisionY){
			setY(oldY);
			velocity.y = 0;
		}		
		
		//checks if monster is hit
		checkCollisionwithMonster();

		//check player health
		if(health <= 0){
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 0);
			setY(oldY);
			setX(oldX);
		}else{
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
		}
		
		givePostionToSword(getX(),getY());
		
		// update animation
		animationTime += delta;
	//	setRegion(velocity.x < 0 ? left.getKeyFrame(animationTime) : velocity.x > 0 ? right.getKeyFrame(animationTime) : velocity.y > 0 ? up.getKeyFrame(animationTime) : velocity.y < 0 ? down.getKeyFrame(animationTime) : still.getKeyFrame(animationTime));
		switch(direction){
		case 0:
			setRegion(left.getKeyFrame(animationTime));
			break;
		case 1:
			setRegion(right.getKeyFrame(animationTime));
			break;
		case 2:
			setRegion(down.getKeyFrame(animationTime));
			break;
		case 3:
			setRegion(up.getKeyFrame(animationTime));
			break;
		}
		if(velocity.x == 0 && velocity.y == 0) animationTime = 0;

	}

	private void checkCollisionwithMonster() {
		//set sword bounding box
		r1.set(this.getX(),this.getY(),this.getWidth(),this.getHeight());
		
		//check collision with monsters		
		if(Math.ceil(this.getColor().a) == 1 && System.nanoTime() - oldTime >= cdTime){
			for(TreeMonster treeMon : treeMonArray){
				if(treeMon.isVisible()){
					r2.set(treeMon.getCollisionBox());
					if (!r1.overlaps(r2)) continue;
					oldTime = System.nanoTime();
					setHealth(health-1);
					break;
				}
			}
		}
		
		if(Math.ceil(this.getColor().a) == 1 && System.nanoTime() - oldTime >= cdTime){
			for(SlimeMonster slimeMon : slimeMonArray){
				if(slimeMon.isVisible()){
					r2.set(slimeMon.getCollisionBox());
					if (!r1.overlaps(r2)) continue;
					oldTime = System.nanoTime();
					setHealth(health-1);
					break;
				}
			}
		}
		
		if(Math.ceil(this.getColor().a) == 1 && System.nanoTime() - oldTime >= cdTime){
			for(FireMonster fireMon : fireMonArray){
				if(fireMon.isVisible()){
					r2.set(fireMon.getCollisionBox());
					if (!r1.overlaps(r2)) continue;
					oldTime = System.nanoTime();
					setHealth(health-1);
					break;
				}
			}
		}
	}

	private void givePostionToSword(float x, float y) {
		sword.setPositionPlayer(x,y);
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
				//System.out.println("Rechts Collision");
				return true;}	
		return false;
	}

	public boolean collidesLeft() {
		for(float step = 0; step < pheight; step += collisionLayer.getTileHeight() / 2)
			if(isCellBlocked(_x, _y + step)){
				//System.out.println("Links Collision");
				return true;}
		return false;
	}

	public boolean collidesTop() {
		for(float step = 0; step < pwidth; step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(_x + step, _y + pheight)){
				//System.out.println("Oben Collision");
				return true;}
		return false;
	}

	public boolean collidesBottom() {
		for(float step = 0; step < pwidth; step += collisionLayer.getTileWidth() / 2)
			if(isCellBlocked(_x + step, _y)){
				//System.out.println("Unten Collision");
				return true;}
		return false;
	}
	
	public void moveUp(boolean walk){
		velocity.y = (walk == true) ? speed : 0;
	}
	
	public void moveDown(boolean walk){
		velocity.y = (walk == true) ? -speed : 0;
	}
	
	public void moveRight(boolean walk){
		velocity.x = (walk == true) ? speed : 0;
	}
	
	public void moveLeft(boolean walk){
		velocity.x = (walk == true) ? -speed : 0;
	}
	
	public void swordFireAttack(boolean attack){		
		sword.setSwordFire(attack, direction);
	}
	
	public void swordLightningAttack(boolean attack){
		sword.setSwordLightning(attack, direction);
	}
	
	public void swordWaterAttack(boolean attack){
		sword.setSwordWater(attack, direction);
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

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void updateHeartBar(){
		switch (PLAYER) {
		case 1:
			heartHudSprite = heartHud.createSprite("heart"+health);
			heartHudSprite.setX(camera.position.x-(Gdx.graphics.getWidth()/4));
			heartHudSprite.setY((camera.position.y+(Gdx.graphics.getHeight()/4))-heartHudSprite.getHeight()*2);
			break;
		case 2:
			heartHudSprite = heartHud.createSprite("heart"+health);
			heartHudSprite.setX(camera.position.x+(Gdx.graphics.getWidth()/4)-heartHudSprite.getWidth()*2);
			heartHudSprite.setY((camera.position.y+(Gdx.graphics.getHeight()/4))-heartHudSprite.getHeight()*2);
			break;
		case 3:
			heartHudSprite = heartHud.createSprite("heart"+health);
			heartHudSprite.setX(camera.position.x-(Gdx.graphics.getWidth()/4));
			heartHudSprite.setY(camera.position.y-(Gdx.graphics.getHeight()/4));
			break;
		case 4:
			heartHudSprite = heartHud.createSprite("heart"+health);
			heartHudSprite.setX(camera.position.x+(Gdx.graphics.getWidth()/4)-heartHudSprite.getWidth()*2);
			heartHudSprite.setY(camera.position.y-(Gdx.graphics.getHeight()/4));
			break;
		}
	}
}
