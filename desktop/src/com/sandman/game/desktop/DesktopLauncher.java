package com.sandman.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sandman.game.Sandman;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Sandman Watch";
	
		config.width = 800;
		config.height = 400;
		new LwjglApplication(new Sandman(), config);
	}
}
