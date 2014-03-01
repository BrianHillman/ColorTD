package com.brian.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor{
	Enemy target;
	int speed;
	int damage;
	Sprite sprite;
	String effects;
	
	public Bullet(Enemy target,int damage, int speed, String effects, Sprite sprite, float posx , float posy){
		this.damage = damage;
		this.target = target;
		this.effects = effects;
		this.speed = speed;
		this.sprite = sprite;
		this.setPosition(posx, posy);
	}
	
	
	@Override
	public void act(float delta){
		//move it towards target
		
		//if it's 2 pixels from target it hit.
		
		//if it hit then:
			//apply all HitEffects to target.
			//remove self.
		
		
		
		Vector2 direction = new Vector2(target.getCenter().x - (this.getX()+this.getWidth()/2),
				
								target.getCenter().y - (this.getY()+this.getHeight()/2));
		
		if(Math.abs(direction.x) < 10 & Math.abs(direction.y) <  10) {
			target.handleHit(this);
			remove();
		}
		
		direction.nor();
		direction.set(this.getX() + direction.x*speed*delta, this.getY()+direction.y*speed*delta);
		//direction.set(target.getCenter().x, target.getCenter().y);
		
		setPosition(direction.x,direction.y);
		
		

	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		sprite.setPosition(getX(), getY());
		//Gdx.app.log("bullet", ""+getX() + " , "+getY());
		sprite.draw(batch);
	}
	
}
