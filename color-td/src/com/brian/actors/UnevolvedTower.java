package com.brian.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class UnevolvedTower extends GenericTower{
	public int spawnX;
	public int spawnY;
	float dx = 0;
	float dy = 0;
	final int TOWERSNAPSIZE = 8;
	public boolean validPosition = true;
	
	public UnevolvedTower(int x, int y,final Sprite sprite) {
		
		super(x, y);
		this.sprite = sprite;
		spawnX = x;
		spawnY = y;
		super.name = "Prism";
		super.size( sprite.getWidth(), sprite.getHeight());
		rect = new Rectangle(x,y,sprite.getWidth(),sprite.getHeight());

		
		
		this.addListener((new DragListener() {
			
			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				sprite.setPosition(sprite.getX() + x, sprite.getY() + y);
				setOrigin(Gdx.input.getX(), Gdx.input.getY());
					Vector2 screenCoords = new Vector2();
					localToStageCoordinates(screenCoords.set(x, y));
				 moveTo(screenCoords.x, screenCoords.y);
				 
			}

		}));
		
		this.addListener(new ActorGestureListener() {
			@Override
			public void touchDown(InputEvent event, float x, float y,
				int pointer, int button) {
				setDYDX(x,y);
			}

		});
	
		
	}
	private boolean validPosition(){
		for(int x = 0; x < this.getStage().getActors().size; x++){
			if(this.getStage().getActors().get(x) instanceof GenericTower && this.getStage().getActors().get(x) != this) {
				if(rect.overlaps(((GenericTower)this.getStage().getActors().get(x)).rect)){
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	private void setDYDX(float x, float y){
		dx =  x - sprite.getOriginX() + sprite.getWidth()/2;
		dy =  y - sprite.getOriginY() + sprite.getHeight()/2;
	}
	
	public void  moveTo(float x,float y){
		int roundedX =  (int) (x - dx);
		int roundedY = (int) (y - dy);
		
		
		
		roundedX += (roundedX % TOWERSNAPSIZE < TOWERSNAPSIZE/2) ? 0 : TOWERSNAPSIZE;
		roundedY += (roundedY % TOWERSNAPSIZE < TOWERSNAPSIZE/2) ? 0 : TOWERSNAPSIZE;

		roundedX = (roundedX /TOWERSNAPSIZE ) *TOWERSNAPSIZE;
		roundedY = (roundedY /TOWERSNAPSIZE ) *TOWERSNAPSIZE;
		
		super.setX(roundedX); //round to nearest fifth pixel;
		super.setY(roundedY);
		updateRect();
		validPosition = validPosition();
	}
	
	public void updateRect(){
		rect.setPosition(this.getX(), this.getY());
	}
	
	@Override
	public void act(float delta){
		if(!validPosition){
			validPosition = validPosition();
		}
	}
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		if(validPosition){
			super.draw(batch, parentAlpha);
		}else{
			sprite.setColor(1,1,1,.9f);
			super.draw(batch, parentAlpha);
			sprite.setColor(1,0,0,.5f);
			super.draw(batch, parentAlpha);
			sprite.setColor(1,1,1,1);
			
		}
	}
	
	
	
	
}
