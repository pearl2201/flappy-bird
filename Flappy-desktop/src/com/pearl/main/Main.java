package com.pearl.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Flappy";
		cfg.width = 368;
		cfg.height = 576;
		
		new LwjglApplication(new Pearl(), cfg);
	}
}
