package com.brian.actors;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor{
	Enemy target;
	int speed;
	int damage;
	Sprite sprite;
	String effects;
	
	public Bullet(Enemy target,int damage, int speed, String effects, Sprite sprite){
		this.damage = damage;
		this.target = target;
		this.effects = effects;
		this.sprite = sprite;
	}
	
	
	@Override
	public void act(float delta){
		//move it towards target
		
		//if it's 2 pixels from target it hit.
		
		//if it hit then:
			//apply all HitEffects to target.
			//remove self.
		Vector2 direction = new Vector2(target.getX()-this.getX()+target.getWidth()/2,target.getY()-this.getY()+target.getHeight()/2).nor();
		setPosition(this.getX()+direction.x*speed*delta,this.getY()+direction.y*speed*delta);
		
		if(target.hit(this.getX(), this.getY(), true) != null){
			remove();
		}
		//accelerate
		//causes no collisions
		//speed += Math.pow(1.1, delta);
	}
	
	
	
}
