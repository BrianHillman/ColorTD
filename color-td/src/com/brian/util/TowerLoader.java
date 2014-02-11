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
	private HashMap<String, Sprite> spriteStore = new HashMap<String, Sprite>();
	
	public GenericTower makeTower(String name, int posx, int posy){
		Sprite sprite;
		//special case for unevolved towers
		
		
		MyTowerPrototype temp = null;
		for(int x = 0; x < towers.size() ; x++){
			if(towers.get(x).name.equalsIgnoreCase(name)) temp = towers.get(x);
		}
		if(temp == null){
			System.out.println("Tower not found");
			System.exit(0);
		}
		if(spriteStore.containsKey(temp.sprite.toLowerCase())){
			Gdx.app.log("test", "loaded from spritestore");
			sprite = spriteStore.get(temp.sprite.toLowerCase());
		}else{
		
			sprite= new Sprite(new Texture(Gdx.files.internal("data/sprites/"+temp.sprite.toLowerCase())));
			spriteStore.put(temp.sprite.toLowerCase(), sprite);
		}
		
		GenericTower e = new GenericTower(posx,posy,name,sprite, temp.attackSpeed, temp.damage);
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
		
		Sprite sprite;

		if(spriteStore.containsKey(temp.sprite.toLowerCase())){
			
			sprite = spriteStore.get(temp.sprite.toLowerCase());
		}else{
			Gdx.app.log("test", "loaded from mem");
			sprite = new Sprite(new Texture(Gdx.files.internal("data/sprites/"+temp.sprite.toLowerCase())));
			spriteStore.put(temp.sprite.toLowerCase(), sprite);
		}
		Gdx.app.log("test", "" + (sprite == null));
		return new UnevolvedTower(posx, posy, sprite);
	}
	
}
