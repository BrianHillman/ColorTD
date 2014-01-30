package com.brian.util;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.brian.actors.Enemy;
import com.brian.actors.GenericTower;

public class TowerLoader {
	
	private ArrayList<MyTowerPrototype> towers;
	
	public GenericTower makeTower(String name, int posx, int posy){
		MyTowerPrototype temp = null;
		for(int x = 0; x < towers.size() ; x++){
			if(towers.get(x).name.equalsIgnoreCase(name)) temp = towers.get(x);
		}
		if(temp == null){
			System.out.println("Tower not found");
			System.exit(0);
		}
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("data/sprites/"+temp.sprite.toLowerCase())));
		GenericTower e  = new GenericTower(posx,posy,name,sprite, temp.attackSpeed, temp.damage);
		return e;
	}
}
