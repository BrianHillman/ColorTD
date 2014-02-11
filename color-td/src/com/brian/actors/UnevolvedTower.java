package com.brian.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UnevolvedTower extends GenericTower{
	public int spawnX;
	public int spawnY;
	private Sprite sprite;
	
	public UnevolvedTower(int x, int y,Sprite sprite) {
		super(x, y);
		spawnX = x;
		spawnY = y;
		super.name = "Prism";
		this.sprite = sprite;
		
	}
	public void  moveTo(int x,int y){
		super.setX(((int) x/5)*5); //round to nearest fifth pixel;
		super.setY(((int) y/5)*5);
		
	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		sprite.setPosition(super.getX(), super.getY());
		sprite.draw(batch);
	}
	
	
	
	
}
