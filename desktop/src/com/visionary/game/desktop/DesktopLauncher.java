package com.visionary.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.visionary.game.FlappyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyDemo.width;
		config.height = FlappyDemo.height;
		config.title = FlappyDemo.title;
		new LwjglApplication(new FlappyDemo(), config);
	}
}
