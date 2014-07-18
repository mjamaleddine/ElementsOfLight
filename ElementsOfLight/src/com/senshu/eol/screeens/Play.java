package com.senshu.eol.screeens;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.senshu.eol.EolGame;
import com.senshu.eol.entities.FireMonster;
import com.senshu.eol.entities.Player;
import com.senshu.eol.entities.SlimeMonster;
import com.senshu.eol.entities.Sword;
import com.senshu.eol.entities.TreeMonster;
import com.senshu.eol.input.GamepadInput;

public class Play implements Screen {

	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
		
	private TextureAtlas player1Atlas;
	private Player player1;
	
	Animation player1still, player1left, player1right, player1up, player1down;
	
	private TextureAtlas player2Atlas;
	private Player player2;
	
	Animation player2still, player2left, player2right, player2up, player2down;
	
	private TextureAtlas player3Atlas;
	private Player player3;
	
	Animation player3still, player3left, player3right, player3up, player3down;
	
	private TextureAtlas player4Atlas;
	private Player player4;
	
	Animation player4still, player4left, player4right, player4up, player4down;
	
	private Sword sword1,sword2,sword3,sword4;
	
	private TextureAtlas swordFireAtlas;
	
	Animation swordFireleft, swordFireright, swordFireup, swordFiredown;
	
	private TextureAtlas swordLightningAtlas;
	
	Animation swordLightningleft, swordLightningright, swordLightningup, swordLightningdown;
	
	private TextureAtlas swordWaterAtlas;
	
	Animation swordWaterleft, swordWaterright, swordWaterup, swordWaterdown;
	
	private TextureAtlas treeMonsterAtlas;
	
	Animation treeMonsterup, treeMonsterdown;
	
	private TextureAtlas slimeMonsterAtlas;
	
	Animation slimeMonsterleft, slimeMonsterright;
	
	private TextureAtlas fireMonsterAtlas;
	
	Animation fireMonsterup, fireMonsterdown;
	
	private ArrayList<TreeMonster> treeMonArray;
	private ArrayList<SlimeMonster> slimeMonArray;
	private ArrayList<FireMonster> fireMonArray;

	private Music music;
	
	private BitmapFont font;
	
	private FPSLogger fpsCounter = new FPSLogger();
	private TiledMapTile doorTile;
	
	private int numOfVis;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.position.set(player1.getX() + player1.getWidth() / 2, player1.getY() + player1.getHeight() / 2, 0);
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
		
		renderer.getSpriteBatch().begin();
		
		sword1.draw(renderer.getSpriteBatch());
		sword2.draw(renderer.getSpriteBatch());
//		sword3.draw(renderer.getSpriteBatch());
//		sword4.draw(renderer.getSpriteBatch());
//		
//		player4.draw(renderer.getSpriteBatch());
//		player3.draw(renderer.getSpriteBatch());
		player2.draw(renderer.getSpriteBatch());
		player1.draw(renderer.getSpriteBatch());
		
		numOfVis = 0;
		for(TreeMonster treeMon : treeMonArray){
			treeMon.draw(renderer.getSpriteBatch());
			if(!treeMon.isVisible()){
				numOfVis++;
				if(numOfVis == treeMonArray.size())
					openDoorThree();
			}
	    }
		
		numOfVis = 0;
		for(SlimeMonster slimeMon : slimeMonArray){
			slimeMon.draw(renderer.getSpriteBatch());
			if(!slimeMon.isVisible()){
				numOfVis++;
				if(numOfVis == slimeMonArray.size())
					openDoorOne();
			}
	    }
		
		numOfVis = 0;
		for(FireMonster fireMon : fireMonArray){
			fireMon.draw(renderer.getSpriteBatch());
			if(!fireMon.isVisible()){
				numOfVis++;
				if(numOfVis == fireMonArray.size())
					openDoorTwo();
			}
	    }
		
		//font.draw(renderer.getSpriteBatch(),"Player 1 Health: "+Float.toString(player1.getHealth()), camera.position.x-(Gdx.graphics.getWidth()/4), camera.position.y+(Gdx.graphics.getHeight()/4));
		
		if(EolGame.DEBUG)fpsCounter.log();
		renderer.getSpriteBatch().end();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width / 2;
		camera.viewportHeight = height / 2;
	}

	@Override
	public void show() {
		map = new TmxMapLoader().load("maps/demomap.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		treeMonArray = new ArrayList<TreeMonster>();
		slimeMonArray = new ArrayList<SlimeMonster>();
		fireMonArray = new ArrayList<FireMonster>();
		
		font = new BitmapFont();		
		
		textureAtlasinit();		
			
		animationSetter();
		
		sword1 = new Sword(swordFireleft, swordFireright, swordFireup, swordFiredown, swordLightningleft, swordLightningright, swordLightningup, swordLightningdown, swordWaterleft, swordWaterright, swordWaterup, swordWaterdown, treeMonArray, slimeMonArray, fireMonArray);
		player1 = new Player(player1still, player1left, player1right, player1up , player1down, map ,sword1, treeMonArray, slimeMonArray, fireMonArray, camera);
		sword1.setPosition(35 * player1.getCollisionLayer().getTileWidth(),26 * player1.getCollisionLayer().getTileHeight());
		player1.setPosition(35 * player1.getCollisionLayer().getTileWidth(), 26 * player1.getCollisionLayer().getTileHeight());
		
		sword2 = new Sword(swordFireleft, swordFireright, swordFireup, swordFiredown, swordLightningleft, swordLightningright, swordLightningup, swordLightningdown, swordWaterleft, swordWaterright, swordWaterup, swordWaterdown, treeMonArray, slimeMonArray, fireMonArray);
		sword2.setPosition(10,10);
		player2 = new Player(player2still, player2left, player2right, player2up , player2down, map ,sword2, treeMonArray, slimeMonArray, fireMonArray, camera);
		player2.setPosition(35 * player2.getCollisionLayer().getTileWidth()+32, 26 * player2.getCollisionLayer().getTileHeight());
//		
//		sword3 = new Sword(swordFireleft, swordFireright, swordFireup, swordFiredown, swordLightningleft, swordLightningright, swordLightningup, swordLightningdown, swordWaterleft, swordWaterright, swordWaterup, swordWaterdown, treeMonArray, slimeMonArray, fireMonArray);
//		sword3.setPosition(10,10);
//		player3 = new Player(player3still, player3left, player3right, player3up , player3down, (TiledMapTileLayer) map.getLayers().get(0),sword3, treeMonArray, slimeMonArray, fireMonArray, camera);
//		player3.setPosition(35 * player3.getCollisionLayer().getTileWidth(), 26 * player3.getCollisionLayer().getTileHeight()+32);
//		
//		sword4 = new Sword(swordFireleft, swordFireright, swordFireup, swordFiredown, swordLightningleft, swordLightningright, swordLightningup, swordLightningdown, swordWaterleft, swordWaterright, swordWaterup, swordWaterdown, treeMonArray, slimeMonArray, fireMonArray);
//		sword4.setPosition(10,10);
//		player4 = new Player(player4still, player4left, player4right, player4up , player4down, (TiledMapTileLayer) map.getLayers().get(0),sword4, treeMonArray, slimeMonArray, fireMonArray, camera);
//		player4.setPosition(35 * player4.getCollisionLayer().getTileWidth()+32, 26 * player4.getCollisionLayer().getTileHeight()+32);

		spawnMonster(map);
		
		//Gdx.input.setInputProcessor(new KeyboardInput(player1));
		Gdx.input.setInputProcessor(new GamepadInput(player1,player2,player3,player4));
		
		//Music
		music = Gdx.audio.newMusic(Gdx.files.internal("sound/darklights.mp3"));
		music.setVolume(0.6f);  
		music.setLooping(true);
		music.play();
		

		// DOORS

//		// initialize
		doorTile = null;
//
		// get the open door tile
		Iterator<TiledMapTile> tiles = map.getTileSets().getTileSet("sewer_1").iterator();
		while(tiles.hasNext()) {
			TiledMapTile tile = tiles.next();
			if(tile.getProperties().containsKey("door") && tile.getProperties().get("door", String.class).equals("open")){
				doorTile = tile;
			}
		}
		
	}
	
	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		player1.getTexture().dispose();
		player1Atlas.dispose();
		player2.getTexture().dispose();
		player2Atlas.dispose();
//		player3.getTexture().dispose();
//		player3Atlas.dispose();
//		player4.getTexture().dispose();
//		player4Atlas.dispose();
		
		sword1.getTexture().dispose();
		sword2.getTexture().dispose();
//		sword3.getTexture().dispose();
//		sword4.getTexture().dispose();

		swordFireAtlas.dispose();
		swordLightningAtlas.dispose();
		swordWaterAtlas.dispose();
		
		treeMonsterAtlas.dispose();
		slimeMonsterAtlas.dispose();
		fireMonsterAtlas.dispose();
		
		//music.dispose();
	}
	
	private void textureAtlasinit() {
		
		player1Atlas = new TextureAtlas("img/entities/player/player1.pack");
		player2Atlas = new TextureAtlas("img/entities/player/player2.pack");
		player3Atlas = new TextureAtlas("img/entities/player/player3.pack");
		player4Atlas = new TextureAtlas("img/entities/player/player4.pack");
		
		swordFireAtlas = new TextureAtlas("img/weapons/swordfire.pack");
		swordLightningAtlas = new TextureAtlas("img/weapons/swordlightning.pack");
		swordWaterAtlas = new TextureAtlas("img/weapons/swordwater.pack");
		
		treeMonsterAtlas = new TextureAtlas("img/entities/enemies/tree.pack");
		slimeMonsterAtlas = new TextureAtlas("img/entities/enemies/slime.pack");
		fireMonsterAtlas = new TextureAtlas("img/entities/enemies/fire.pack");
		
	}
	
	private void spawnMonster(TiledMap map) {
		int i = 0;
		for(MapObject object : map.getLayers().get("treeMonster").getObjects()){
			if(object instanceof RectangleMapObject) {
				RectangleMapObject rectObject = (RectangleMapObject) object;
				Rectangle spawn = rectObject.getRectangle();			
				//SPAWN MONSTERS
				treeMonArray.add(new TreeMonster(60, treeMonsterup, treeMonsterdown, camera, player1));
				treeMonArray.get(i).setPosition(spawn.x, spawn.y);
				i++;
			}
		}
		i=0;
		for(MapObject object : map.getLayers().get("slimeMonster").getObjects()){
			if(object instanceof RectangleMapObject) {
				RectangleMapObject rectObject = (RectangleMapObject) object;
				Rectangle spawn = rectObject.getRectangle();			
				//SPAWN MONSTERS
				slimeMonArray.add(new SlimeMonster(20, slimeMonsterleft, slimeMonsterright, camera, player1));
				slimeMonArray.get(i).setPosition(spawn.x, spawn.y);
				i++;
			}
		}
		i=0;
		for(MapObject object : map.getLayers().get("fireMonster").getObjects()){
			if(object instanceof RectangleMapObject) {
				RectangleMapObject rectObject = (RectangleMapObject) object;
				Rectangle spawn = rectObject.getRectangle();			
				//SPAWN MONSTERS
				fireMonArray.add(new FireMonster(40, fireMonsterup, fireMonsterdown, camera, player1));
				fireMonArray.get(i).setPosition(spawn.x, spawn.y);
				i++;
			}
		}
	}
	

	
	public void animationSetter(){
		
		player1still = new Animation(1 / 15f, player1Atlas.findRegions("still"));
		player1left = new Animation(1 / 15f, player1Atlas.findRegions("left"));
		player1right = new Animation(1 / 15f, player1Atlas.findRegions("right"));
		player1up = new Animation(1 / 15f, player1Atlas.findRegions("up"));
		player1down = new Animation(1 / 15f, player1Atlas.findRegions("down"));
		
		player1still.setPlayMode(Animation.LOOP);
		player1left.setPlayMode(Animation.LOOP);
		player1right.setPlayMode(Animation.LOOP);
		player1up.setPlayMode(Animation.LOOP);
		player1down.setPlayMode(Animation.LOOP);
		
		player2still = new Animation(1 / 15f, player2Atlas.findRegions("still"));
		player2left = new Animation(1 / 15f, player2Atlas.findRegions("left"));
		player2right = new Animation(1 / 15f, player2Atlas.findRegions("right"));
		player2up = new Animation(1 / 15f, player2Atlas.findRegions("up"));
		player2down = new Animation(1 / 15f, player2Atlas.findRegions("down"));
		
		player2still.setPlayMode(Animation.LOOP);
		player2left.setPlayMode(Animation.LOOP);
		player2right.setPlayMode(Animation.LOOP);
		player2up.setPlayMode(Animation.LOOP);
		player2down.setPlayMode(Animation.LOOP);
		
		player3still = new Animation(1 / 15f, player3Atlas.findRegions("still"));
		player3left = new Animation(1 / 15f, player3Atlas.findRegions("left"));
		player3right = new Animation(1 / 15f, player3Atlas.findRegions("right"));
		player3up = new Animation(1 / 15f, player3Atlas.findRegions("up"));
		player3down = new Animation(1 / 15f, player3Atlas.findRegions("down"));
		
		player3still.setPlayMode(Animation.LOOP);
		player3left.setPlayMode(Animation.LOOP);
		player3right.setPlayMode(Animation.LOOP);
		player3up.setPlayMode(Animation.LOOP);
		player3down.setPlayMode(Animation.LOOP);
		
		player4still = new Animation(1 / 15f, player4Atlas.findRegions("still"));
		player4left = new Animation(1 / 15f, player4Atlas.findRegions("left"));
		player4right = new Animation(1 / 15f, player4Atlas.findRegions("right"));
		player4up = new Animation(1 / 15f, player4Atlas.findRegions("up"));
		player4down = new Animation(1 / 15f, player4Atlas.findRegions("down"));
		
		player4still.setPlayMode(Animation.LOOP);
		player4left.setPlayMode(Animation.LOOP);
		player4right.setPlayMode(Animation.LOOP);
		player4up.setPlayMode(Animation.LOOP);
		player4down.setPlayMode(Animation.LOOP);
		
		swordFireleft = new Animation(1 / 30f, swordFireAtlas.findRegions("left"));
		swordFireright = new Animation(1 / 30f, swordFireAtlas.findRegions("right"));
		swordFireup = new Animation(1 / 30f, swordFireAtlas.findRegions("up"));
		swordFiredown = new Animation(1 / 30f, swordFireAtlas.findRegions("down"));
		
		swordFiredown.setPlayMode(Animation.NORMAL);
		swordFireleft.setPlayMode(Animation.NORMAL);
		swordFireright.setPlayMode(Animation.NORMAL);
		swordFireup.setPlayMode(Animation.NORMAL);
		
		swordLightningleft = new Animation(1 / 30f, swordLightningAtlas.findRegions("left"));
		swordLightningright = new Animation(1 / 30f, swordLightningAtlas.findRegions("right"));
		swordLightningup = new Animation(1 / 30f, swordLightningAtlas.findRegions("up"));
		swordLightningdown = new Animation(1 / 30f, swordLightningAtlas.findRegions("down"));
		
		swordLightningdown.setPlayMode(Animation.LOOP);
		swordLightningleft.setPlayMode(Animation.LOOP);
		swordLightningright.setPlayMode(Animation.LOOP);
		swordLightningup.setPlayMode(Animation.LOOP);
		
		swordWaterleft = new Animation(1 / 30f, swordWaterAtlas.findRegions("left"));
		swordWaterright = new Animation(1 / 30f, swordWaterAtlas.findRegions("right"));
		swordWaterup = new Animation(1 / 30f, swordWaterAtlas.findRegions("up"));
		swordWaterdown = new Animation(1 / 30f, swordWaterAtlas.findRegions("down"));
		
		swordWaterdown.setPlayMode(Animation.LOOP);
		swordWaterleft.setPlayMode(Animation.LOOP);
		swordWaterright.setPlayMode(Animation.LOOP);
		swordWaterup.setPlayMode(Animation.LOOP);
		
		treeMonsterup = new Animation(1 / 15f, treeMonsterAtlas.findRegions("up"));
		treeMonsterdown = new Animation(1 / 15f, treeMonsterAtlas.findRegions("down"));

		treeMonsterup.setPlayMode(Animation.LOOP);
		treeMonsterdown.setPlayMode(Animation.LOOP);
		
		slimeMonsterleft = new Animation(1 / 15f, slimeMonsterAtlas.findRegions("left"));
		slimeMonsterright = new Animation(1 / 15f, slimeMonsterAtlas.findRegions("right"));

		slimeMonsterleft.setPlayMode(Animation.LOOP);
		slimeMonsterright.setPlayMode(Animation.LOOP);
		
		fireMonsterup = new Animation(1 / 15f, fireMonsterAtlas.findRegions("up"));
		fireMonsterdown = new Animation(1 / 15f, fireMonsterAtlas.findRegions("down"));

		fireMonsterup.setPlayMode(Animation.LOOP);
		fireMonsterdown.setPlayMode(Animation.LOOP);
	}
	
	public void openDoorOne(){
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");

		for(int x = 0; x < layer.getWidth(); x++)
			for(int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				if(cell != null && cell.getTile().getProperties().containsKey("door") && cell.getTile().getProperties().get("door", String.class).equals("locked"))
					if(true)
						cell.setTile(doorTile);
		}
	}
	
	public void openDoorTwo(){
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");

		for(int x = 0; x < layer.getWidth(); x++)
			for(int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				if(cell != null && cell.getTile().getProperties().containsKey("door") && cell.getTile().getProperties().get("door", String.class).equals("locked2"))
					if(true)
						cell.setTile(doorTile);
		}
	}
	
	public void openDoorThree(){
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");

		for(int x = 0; x < layer.getWidth(); x++)
			for(int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				if(cell != null && cell.getTile().getProperties().containsKey("door") && cell.getTile().getProperties().get("door", String.class).equals("locked3"))
					if(true)
						cell.setTile(doorTile);
		}
	}

}
