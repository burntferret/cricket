package com.stag.cricket.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.stag.cricket.MainCricket;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = MainCricket.HEIGHT;
		config.width = MainCricket.WIDTH;
		
		new LwjglApplication(new MainCricket(), config);
	}
}
