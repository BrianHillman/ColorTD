package com.brian.actors;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class KeepButton extends Actor	{
	final String text = "Keep";
	BitmapFont font = new BitmapFont(); ;
	final float posX = 420;
	final float posY = 30;
	
	public KeepButton(){
		super.setOrigin(posX, posX);
		super.setBounds(posX,posY - font.getBounds(text).height, font.getBounds(text).width , font.getBounds(text).height );
		
	}
	
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {

		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(batch, text, posX, posY);
	}
}
