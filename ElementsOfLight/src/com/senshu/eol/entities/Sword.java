package com.senshu.eol.entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Sword extends Sprite {

	private float animationTime = 0;
	
	Animation swordFireleft, swordFireright,
	 swordFireup,  swordFiredown,
	 swordLightningleft,  swordLightningright,
	 swordLightningup,  swordLightningdown,
	 swordWaterleft,  swordWaterright,
	 swordWaterup,  swordWaterdown;

	private boolean swordFireAttack = false;

	private boolean swordLightningAttack = false;

	private boolean swordWaterAttack = false;

	private int attackDirection;

	private float playerX, playerY;
	
	private Rectangle r1, r2;

	private ArrayList<TreeMonster> treeMonArray;
	private ArrayList<SlimeMonster> slimeMonArray;
	private ArrayList<FireMonster> fireMonArray;
	
	private Sound sound;
	private float soundVolume = 1.0f;

	private float oldTime, cdTime = 200000000;

	public Sword(Animation swordFireleft, Animation swordFireright,
			Animation swordFireup, Animation swordFiredown,
			Animation swordLightningleft, Animation swordLightningright,
			Animation swordLightningup, Animation swordLightningdown,
			Animation swordWaterleft, Animation swordWaterright,
			Animation swordWaterup, Animation swordWaterdown, ArrayList<TreeMonster> treeMonArray,  ArrayList<SlimeMonster> slimeMonArray, ArrayList<FireMonster> fireMonArray) {
		 super(swordFireleft.getKeyFrame(0));
		 this.swordFireleft = swordFireleft;
		 this.swordFireright = swordFireright;
		 this.swordFireup = swordFireup;
		 this.swordFiredown = swordFiredown;
		 this.swordLightningleft = swordLightningleft;
		 this.swordLightningright = swordLightningright;
		 this.swordLightningup = swordLightningup;
		 this.swordLightningdown = swordLightningdown;
		 this.swordWaterleft = swordWaterleft;
		 this.swordWaterright = swordWaterright;
		 this.swordWaterup = swordWaterup;
		 this.swordWaterdown = swordWaterdown;
		 this.treeMonArray = treeMonArray;
		 this.slimeMonArray = slimeMonArray;
		 this.fireMonArray = fireMonArray;
		 
		 sound = Gdx.audio.newSound(Gdx.files.internal("sound/swordswing.wav"));
		 
		 r1 = new Rectangle();
		 r2 = new Rectangle();
	}

	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	public void update(float delta){
		animationTime += delta;
		if(swordFireAttack){
			switch(attackDirection){
				case 0:
					this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
					setRegion(swordFireleft.getKeyFrame(animationTime));
					this.setPosition(playerX - 25, playerY);
					if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
					if(swordFireleft.isAnimationFinished(animationTime)) swordFireAttack = false; 
					break;
				case 1:
					setRegion(swordFireright.getKeyFrame(animationTime));
					this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
					this.setPosition(playerX + 25, playerY);
					if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
					if(swordFireright.isAnimationFinished(animationTime)) swordFireAttack = false;
					break;
				case 2:
					setRegion(swordFiredown.getKeyFrame(animationTime));
					this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
					this.setPosition(playerX, playerY - 30);
					if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
					if(swordFiredown.isAnimationFinished(animationTime)) swordFireAttack = false;
					break;
				case 3:
					setRegion(swordFireup.getKeyFrame(animationTime));
					this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
					this.setPosition(playerX, playerY + 30);
					if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
					if(swordFireup.isAnimationFinished(animationTime)) swordFireAttack = false;
					break;
			}
		}else if(swordLightningAttack){
			switch(attackDirection){
			case 0:
				this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
				setRegion(swordLightningleft.getKeyFrame(animationTime));
				this.setPosition(playerX - 25, playerY);
				if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
				if(swordLightningleft.isAnimationFinished(animationTime)) swordLightningAttack = false;
				break;
			case 1:
				this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
				setRegion(swordLightningright.getKeyFrame(animationTime));
				this.setPosition(playerX + 25, playerY);
				if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
				if(swordLightningright.isAnimationFinished(animationTime)) swordLightningAttack = false;
				break;
			case 2:
				this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
				setRegion(swordLightningdown.getKeyFrame(animationTime));
				this.setPosition(playerX, playerY - 30);
				if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
				if(swordLightningdown.isAnimationFinished(animationTime)) swordLightningAttack = false;
				break;
			case 3:
				this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
				setRegion(swordLightningup.getKeyFrame(animationTime));
				this.setPosition(playerX, playerY + 30);
				if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
				if(swordLightningup.isAnimationFinished(animationTime)) swordLightningAttack = false;
				break;
		}
		}else if(swordWaterAttack){
			switch(attackDirection){
			case 0:
				this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
				setRegion(swordWaterleft.getKeyFrame(animationTime));
				this.setPosition(playerX - 25, playerY);
				if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
				if(swordWaterleft.isAnimationFinished(animationTime)) swordWaterAttack = false;
				break;
			case 1:
				this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
				setRegion(swordWaterright.getKeyFrame(animationTime));
				this.setPosition(playerX + 25, playerY);
				if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
				if(swordWaterright.isAnimationFinished(animationTime)) swordWaterAttack = false;
				break;
			case 2:
				this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
				setRegion(swordWaterdown.getKeyFrame(animationTime));
				this.setPosition(playerX, playerY - 30);
				if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
				if(swordWaterdown.isAnimationFinished(animationTime)) swordWaterAttack = false;
				break;
			case 3:
				this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 1);
				setRegion(swordWaterup.getKeyFrame(animationTime));
				this.setPosition(playerX, playerY + 30);
				if(System.nanoTime() - oldTime >= cdTime){ sound.play(soundVolume); oldTime = System.nanoTime();}
				if(swordWaterup.isAnimationFinished(animationTime)) swordWaterAttack = false;
				break;
		}
		}else{
			this.setColor(this.getColor().r, this.getColor().g, this.getColor().b, 0);
			this.setPosition(playerX, playerY);
			animationTime = 0;
		}
		
		//checks if monster is hit
		checkCollisionwithMonster();

	}
	
	private void checkCollisionwithMonster() {
		//set sword bounding box
		r1.set(this.getX(),this.getY(),this.getWidth(),this.getHeight());		
		
		if(Math.ceil(this.getColor().a) == 1 && swordFireAttack == true){
			for(TreeMonster treeMon : treeMonArray){
				r2.set(treeMon.getX(), treeMon.getY(), treeMon.getWidth(), treeMon.getHeight());
				if (!r1.overlaps(r2)) continue;
				treeMon.setHealth(treeMon.getHealth()-1);
				break;
			}
		}
		
		if(Math.ceil(this.getColor().a) == 1 && swordLightningAttack == true){
			for(SlimeMonster slimeMon : slimeMonArray){
				r2.set(slimeMon.getX(), slimeMon.getY(), slimeMon.getWidth(), slimeMon.getHeight());
				if (!r1.overlaps(r2)) continue;
				slimeMon.setHealth(slimeMon.getHealth()-1);
				break;
			}
		}
		
		if(Math.ceil(this.getColor().a) == 1 && swordWaterAttack == true){
			for(FireMonster fireMon : fireMonArray){
				r2.set(fireMon.getX(), fireMon.getY(), fireMon.getWidth(), fireMon.getHeight());
				if (!r1.overlaps(r2)) continue;
				fireMon.setHealth(fireMon.getHealth()-1);
				break;
			}
		}
	}

	public void setSwordFire(boolean attack, int direction){
		swordFireAttack = attack;
		attackDirection = direction;
		
	}

	public void setSwordLightning(boolean attack, int direction){
		swordLightningAttack = attack;
		attackDirection = direction;
	}

	public void setSwordWater(boolean attack, int direction){
		swordWaterAttack = attack;
		attackDirection = direction;
	}

	public void setPositionPlayer(float x, float y) {
		playerX = x;
		playerY = y;
	}
	
	
}
