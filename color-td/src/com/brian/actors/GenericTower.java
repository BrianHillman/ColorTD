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
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.brian.util.MyBulletPrototype;

public class GenericTower extends Actor{
	public String name;
	   public int attackSpeed;
	   public int range;
	   public Sprite sprite;
	   public Sprite highlightedSprite;
	   public MyBulletPrototype bullet; 
	   
	   
	   public int damage;
	   public boolean isTouched;
	   private float timeSinceLastAttack;
	   
	   public boolean canAttack = true;
	   
	public GenericTower(int x, int y, String name, Sprite sprite, int attackSpeed, int damage,int range, MyBulletPrototype bullet){
		super.setX(x);
		super.setY(y);
		this.sprite = sprite;
		this.attackSpeed = attackSpeed;
		this.name = name;
		this.damage = damage; 
		isTouched = false;
		this.bullet = bullet;
		this.range = range;
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
	
	@Override 
	public void act(float delta){
		if(attackSpeed == 0){
			
		}else if(timeSinceLastAttack > 1.0f/attackSpeed ){

			if(AttemptAttack()){
				timeSinceLastAttack = 0;
			}
		}else{
			timeSinceLastAttack += delta;
		}
		
	}
	
	private void addListener() {

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
				Gdx.app.log("TouchDown", name);
			}

		});

		

	}
	
	private boolean AttemptAttack(){
		
		for(int x = 0; x < super.getStage().getActors().size; x++){
			if(super.getStage().getActors().get(x) instanceof Enemy){
				if(!((Enemy) super.getStage().getActors().get(x)).isVisible()){
					continue;
				}
				
				//perform range check
				Vector2 enemyPos = ((Enemy) super.getStage().getActors().get(x)).getCenter();
				double dist =  Math.sqrt( Math.pow(enemyPos.x - super.getX() + super.getWidth(), 2)  +  
						Math.pow(enemyPos.y - super.getY() + super.getHeight(), 2));
				
				if(dist <= range ){
					Bullet shot  = new Bullet((Enemy) super.getStage().getActors().get(x), bullet.damage, bullet.speed, bullet.type, bullet.sprite, sprite.getHeight()/2 + getX(), sprite.getWidth()/2 + getY());
					super.getStage().addActor(shot);
					Gdx.app.log("Attacking", "PEW PEW PEW " + ((Enemy) super.getStage().getActors().get(x)).hashCode());
					return true;
				}
				
			}
			
			
		}
		
		
		return false;
	}
}
