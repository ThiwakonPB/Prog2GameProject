package com.prog2game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.prog2game.MyGdxGame;
import com.prog2game.handlers.JSONHandler;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("No Man's Tower");
		config.setResizable(false);
		new Lwjgl3Application(new MyGdxGame(), config);

//		JSONHandler j = new JSONHandler();
//		j.writeEnemyToJSON();
	}
}
