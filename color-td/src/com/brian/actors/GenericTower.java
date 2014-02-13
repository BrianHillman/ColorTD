package com.brian.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class GenericTower extends Actor{
	 public String name;
	   public int attackSpeed;
	   public Sprite sprite;
	   public Sprite highlightedSprite;
	   public int damage;
	public boolean isTouched;

	
	public GenericTower(int x, int y, String name, Sprite sprite, int attackSpeed, int damage){
		super.setX(x);
		super.setY(y);
		this.sprite = sprite;
		this.attackSpeed = attackSpeed;
		this.name = name;
		this.damage = damage; 
		isTouched = false;
		
		
		addListener();


	}
	

	
	public GenericTower(int x, int y){
		super.setX(x);
		super.setY(y);
		//TODO set sprite
		isTouched = false;
		addListener();
		
		
	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		sprite.setPosition(super.getX(), super.getY());
		sprite.draw(batch);
		
		//draws the border around the sprite
		if (isTouched ){
			
			highlightedSprite.setPosition(super.getX()-(highlightedSprite.getWidth() - super.getWidth())/2, super.getY()-(highlightedSprite.getHeight() - super.getHeight())/2);

			highlightedSprite.draw(batch);
			
		}
	}
	private void addListener(){
		Gdx.app.log("reg", "listener");
		this.addListener(new ActorGestureListener() {
			
			
			@Override
	        public void touchUp(InputEvent event, float x, float y,
	                int pointer, int button) {
	                Gdx.app.log("Mouse", "up");
	        }
			
			@Override
			public void touchDown(InputEvent event, float x, float y,
		               int pointer, int button) {
						isTouched = true;
		            //do your stuff it will work when u touched your actor
		               Gdx.app.log("Mouse", "down");
		        }
		
		});
		
	}
	
}
