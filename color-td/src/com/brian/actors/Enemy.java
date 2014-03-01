package com.brian.actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.brian.util.Pair;



public class Enemy extends Actor{
	Array<Pair> path;
	
	//Which step in the path we are to approach;
	int step;
	
	int speed;
	//hp and max hp
	int health;
	int maxHealth;
	Sprite sprite;
	public Sprite highlightedSprite;
	boolean flying;
	String name;
	
	//countdown of the time until this unit is spawned. 
	float timeTillSpawn;
	
	boolean spawned;
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	public boolean isTouched;
	
	public Enemy(String name, int hp, int speed, Sprite sprite, Boolean flying,Array<Pair> path, float timeTillSpawn){
		this.name = name;
		this.maxHealth = hp;
		this.health = hp;
		this.speed = speed;
		spawned = false; 
		this.sprite = sprite;
		this.flying = flying;
		step = 0;
		this.path = path;
		this.timeTillSpawn = timeTillSpawn;
		super.setBounds(0, 0, sprite.getWidth(), sprite.getHeight());
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
		super.setVisible(false);
		Gdx.app.log("Enemy", "Created enemy " + name + "  HP: "+hp);
		
	}
	
	@Override
	public void act(float delta){
		if(super.getWidth() == 0){
				Gdx.app.log("err", "bad enemy width");
		}
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
					Gdx.app.log("Enemy", "BOOM!!! ENEMY REACHED BASE");
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
		super.setVisible(true);
		spawned = true;
	}
	
	
	public void drawBorder(){
		Vector2 stageCoords = new Vector2();
		localToParentCoordinates(stageCoords.set(this.getX(),this.getY()));
		ShapeRenderer shapeRenderer = new ShapeRenderer(100);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(0,1,0,1);
		shapeRenderer.rect(stageCoords.x-5, stageCoords.y-5, 1, 1);
		//shapeRenderer.rect(stageCoords.x-5, stageCoords.y-5, super.getWidth()+10, super.getHeight()+10);
		shapeRenderer.end();	
		
		
	}
	public void drawHealthBar(){
	      float hpdepletion =  ((super.getWidth()/2) * (1f-(((float)health)/((float)maxHealth))));
	      
			
	      shapeRenderer.setColor(1, 0, 0, 1f);
	      shapeRenderer.setProjectionMatrix(super.getStage().getCamera().combined);		

	      shapeRenderer.begin(ShapeType.Filled);
	      Vector2 stageCoords = new Vector2();
	      
	      stageCoords.set(this.getHeight()+this.getX(),this.getY());

	      shapeRenderer.rect(this.getX() + hpdepletion ,this.getHeight() + this.getY(), this.getWidth() - hpdepletion*2, 5);
	      
	      //shapeRenderer.filledRect(-25f, -25f, 50, 50, Color.RED, greenClear, greenClear, Color.RED);
	      shapeRenderer.end();
	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		sprite.setPosition(super.getX(), super.getY());
		highlightedSprite.setPosition(super.getX()-(highlightedSprite.getWidth() - super.getWidth())/2, super.getY()-(highlightedSprite.getHeight() - super.getHeight())/2);
		if(spawned)sprite.draw(batch);
		
		if(health < maxHealth){
			batch.end();
			drawHealthBar();
			batch.begin();
		}
		
		
		
		if (isTouched ){
			
			
			highlightedSprite.draw(batch);
			
		}
	
	}
	
	public boolean handleHit(Bullet x){
		this.health -= x.damage;
		
		if(this.health <= 0){
			this.remove();
		}
		
		return true;
		
	}
	
	public Vector2 getCenter(){
		Vector2 z = new Vector2(super.getWidth()/2 + super.getX(), super.getHeight()/2 + super.getY());
		return z;
		
	}
}
