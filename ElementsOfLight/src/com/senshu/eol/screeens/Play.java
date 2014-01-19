package com.senshu.eol.screeens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.senshu.eol.entities.Player;
import com.senshu.eol.input.GamepadInput;
import com.senshu.eol.input.KeyboardInput;

public class Play implements Screen {

	
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	
	private TextureAtlas playerAtlas;
	private Player player;
		
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
		camera.update();
		
		renderer.setView(camera);
		renderer.render();
		
		renderer.getSpriteBatch().begin();
		player.draw(renderer.getSpriteBatch());
		renderer.getSpriteBatch().end();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width / 2;
		camera.viewportHeight = height / 2;
	}

	@Override
	public void show() {
		map = new TmxMapLoader().load("maps/map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		camera = new OrthographicCamera();
		playerAtlas = new TextureAtlas("img/player.pack");
		
		Animation still, left, right, up, down;
		still = new Animation(1 / 15f, playerAtlas.findRegions("still"));
		left = new Animation(1 / 15f, playerAtlas.findRegions("left"));
		right = new Animation(1 / 15f, playerAtlas.findRegions("right"));
		up = new Animation(1 / 15f, playerAtlas.findRegions("up"));
		down = new Animation(1 / 15f, playerAtlas.findRegions("down"));
		
		still.setPlayMode(Animation.LOOP);
		left.setPlayMode(Animation.LOOP);
		right.setPlayMode(Animation.LOOP);
		up.setPlayMode(Animation.LOOP);
		down.setPlayMode(Animation.LOOP);
		
		player = new Player(still, left, right, up , down, (TiledMapTileLayer) map.getLayers().get(0));
		player.setPosition(35 * player.getCollisionLayer().getTileWidth(), 26 * player.getCollisionLayer().getTileHeight());

		Gdx.input.setInputProcessor(new KeyboardInput(player));
		//Gdx.input.setInputProcessor(new GamepadInput(player));
		
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
		player.getTexture().dispose();
		playerAtlas.dispose();
	}

}
