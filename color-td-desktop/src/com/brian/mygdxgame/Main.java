package com.brian.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.brian.mainscreens.ColorTD;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "color-td";
		cfg.useGL20 = false;
		cfg.width = 800;
		cfg.height = 480;
		
		
		/*speedspeedspeedspeed
		 * Enable to uncap fps, ~ 4700 now :)
		 * 
		 */		
		//cfg.vSyncEnabled = false; 
		//cfg.foregroundFPS = 0;
		
		new LwjglApplication(new ColorTD(), cfg);
		
	}
}
