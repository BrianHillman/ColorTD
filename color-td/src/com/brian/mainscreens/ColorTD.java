package com.brian.mainscreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;




public class ColorTD extends Game {
    MainMenuScreen mainMenuScreen;
    GameScreen gameScreen;
     
	@Override
	public void create() {		

		mainMenuScreen = new MainMenuScreen();
		gameScreen = new GameScreen();
	    Gdx.input.setInputProcessor(gameScreen);

		setScreen(gameScreen); 
	}
}
