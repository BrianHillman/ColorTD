package com.brian.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.brian.actors.Enemy;




public class EnemyLoader {
	
	private ArrayList<MyEnemyPrototype> enemies;
	private HashMap<String, Sprite> spriteStore = new HashMap<String, Sprite>();

	
	
	public Enemy makeEnemy(String name, Array<Pair> path, float timeTillSpawn){
		MyEnemyPrototype temp = null;

		for(int x = 0; x < enemies.size() ; x++){
			if(enemies.get(x).name.equalsIgnoreCase(name)) temp = enemies.get(x);
		}
		if(temp == null){
			System.out.println("Enemy not found");
			System.exit(0);
		}
		
		Sprite sprite = getSprite(temp.sprite);;
		
	//	Sprite sprite = new Sprite(new Texture(Gdx.files.internal("data/sprites/"+temp.sprite.toLowerCase())));
		Enemy e  = new Enemy(temp.name, temp.maxHealth, temp.speed, sprite, temp.flying, path,timeTillSpawn);
		
		e.highlightedSprite = getSprite(((int)sprite.getHeight())+".png");
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


