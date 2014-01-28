package com.me.actors;

import mgmt.Pair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;



public class Enemy extends Actor{
	Array<Pair> path;
	
	//Which step in the path we are to approach;
	int step;
	
	int speed;
	//hp and max hp
	int health;
	int maxHealth;
	Sprite sprite;
	
	boolean flying;
	String name;
	
	//countdown of the time until this unit is spawned. 
	float timeTillSpawn;
	
	boolean spawned;
	
	public Enemy(String name, int hp, int speed, Sprite sprite, Boolean flying,Array<Pair> path, float timeTillSpawn){
		this.name = name;
		this.maxHealth = hp;
		this.speed = speed;
		spawned = false; 
		this.sprite = sprite;
		this.flying = flying;
		step = 0;
		this.path = path;
		this.timeTillSpawn = timeTillSpawn;

	}
	
	@Override
	public void act(float delta){
		
		super.act(delta);
		
		if(!spawned){
			timeTillSpawn -= delta;
			if(timeTillSpawn <= 0){
				spawn();
			}else{
				return;
			}
		}else{
			if(super.getActions().size == 0){
				
				//get next moveto position
				
				if(step >= path.size) {
					this.remove();
					return;
				}
				
				int nextX = path.get(step).x;
				int nextY = path.get(step).y; 
				
				
				
				
				// calculate distance
				
				int distance = (int) Math.sqrt(Math.pow((nextX-getX()),2) + Math.pow((nextY-getY()),2));
				
				
				//move there with an action
				//System.out.println("movto x:"+nextX + " y:" + nextY + " taking "+distance/(1.0f*speed)+ " to go "+distance);
				
				//Actions.moveTo((float) nextX, (float) nextY , distance/(1.0f*speed));
				super.addAction(Actions.moveTo((float) nextX, (float) nextY , distance/(1.0f*speed)));
				step += 1;
			}
		}
	}
	
	
	private void spawn(){
		spawned = true;
		
	}
	
	
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		sprite.setPosition(super.getX(), super.getY());
		if(spawned)sprite.draw(batch);
	
	}
	
	
}
