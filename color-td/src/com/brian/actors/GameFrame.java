package com.brian.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameFrame extends Actor{
	Sprite gameFrameWhole = new Sprite(new Texture(Gdx.files.internal("data/sprites/PlayAreaBorder.png")));
	
	public void draw (SpriteBatch batch, float parentAlpha) {
		gameFrameWhole.draw(batch);
	}
	
	
}
