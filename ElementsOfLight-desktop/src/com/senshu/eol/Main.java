package com.senshu.eol;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Elements of Light " + EolGame.VERSION;
		cfg.useGL20 = true;
		cfg.width = 1280;
		cfg.height = 720;
		cfg.fullscreen = false;
		
		new LwjglApplication(new EolGame(), cfg);
	}
}
