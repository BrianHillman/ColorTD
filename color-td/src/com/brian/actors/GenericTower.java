package com.brian.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GenericTower extends Actor{
	 public String name;
	   public int attackSpeed;
	   public Sprite sprite;
	   public int damage;
	
	public GenericTower(int x, int y, String name, Sprite sprite, int attackSpeed, int damage){
		super.setX(x);
		super.setY(y);
		this.sprite = sprite;
		this.attackSpeed = attackSpeed;
		this.name = name;
		this.damage = damage; 
		
		
	}
	public GenericTower(int x, int y){
		super.setX(x);
		super.setY(y);
		//TODO set sprite
		
	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		sprite.setPosition(super.getX(), super.getY());
		sprite.draw(batch);
	
	}
}
