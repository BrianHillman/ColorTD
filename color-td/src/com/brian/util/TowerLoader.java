package com.brian.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.brian.actors.GenericTower;
import com.brian.actors.UnevolvedTower;

public class TowerLoader {
	
	private ArrayList<MyTowerPrototype> towers;
	private ArrayList<MyBulletPrototype> bullets;
	
	private HashMap<String, Sprite> spriteStore = new HashMap<String, Sprite>();
	
	public GenericTower makeTower(String name, int posx, int posy){
		//special case for unevolved towers
		
		
		MyTowerPrototype temp = null;
		for(int x = 0; x < towers.size() ; x++){
			if(towers.get(x).name.equalsIgnoreCase(name)) temp = towers.get(x);
		}
		if(temp == null){
			System.out.println("Tower not found");
			System.exit(1);
		}
		
		
		MyBulletPrototype bullet = null ;
		for(int x = 0; x < bullets.size() ; x++){
			if(bullets.get(x).name.equalsIgnoreCase(name)) bullet = bullets.get(x);
		}
		if(temp == null){
			System.out.println("Bullet not found");
			System.exit(1);
		}
		
		Sprite sprite = getSprite(temp.sprite);
		
		if(bullet != null){
			Sprite bulletSprite = getSprite(bullet.spriteName);
			bullet.sprite = bulletSprite;
			bullet.damage = temp.damage;
		}
		GenericTower e = new GenericTower(posx,posy,name,sprite, temp.attackSpeed, temp.damage, bullet);
		
		if(bullet == null)e.canAttack = false; 
		
		e.highlightedSprite = getSprite(((int)sprite.getHeight())+"_border.png");
		return e;
	}
	
	public UnevolvedTower makeUnevolvedTower(int posx, int posy){
		MyTowerPrototype temp = null;
		for(int x = 0; x < towers.size() ; x++){
			if(towers.get(x).name.equalsIgnoreCase("prism")) temp = towers.get(x);
		}
		if(temp == null){
			System.out.println("Tower not found");
			System.exit(0);
		}
		
		Sprite sprite = getSprite(temp.sprite);

		
		Gdx.app.log("test", "" + (sprite == null));
		
		UnevolvedTower e = new UnevolvedTower(posx, posy, sprite);
		e.highlightedSprite = getSprite(((int)sprite.getHeight()+"_border.png"));
		return e; 
	}
	
	
	
	public Sprite getSprite(String sprite){
		Sprite tempsprite;

		if(spriteStore.containsKey(sprite.toLowerCase())){
			tempsprite = spriteStore.get(sprite.toLowerCase());
		}else{
		
			tempsprite = new Sprite(new Texture(Gdx.files.internal("data/sprites/"+sprite.toLowerCase())));
			spriteStore.put(sprite.toLowerCase(), tempsprite);
		}
		return tempsprite;
	}
	
	
}
