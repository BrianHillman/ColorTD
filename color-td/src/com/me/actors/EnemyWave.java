package com.me.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

public class EnemyWave extends Actor{
	//enemy waves read enemy info from a file. 
	Array<Enemy> enemies;
	private Stage stage;

	//we also need a path for the enemies to follow. 
	public EnemyWave(String waveName){
		stage = new Stage();
		
		Json json = new Json();
		FileHandle handle = Gdx.files.internal("data/units.json");
		Enemy temp = json.fromJson(Enemy.class, handle);
		//load wave file
		
		//parse it populating enemies array.
		
	}
	
	@Override
	public void act(float deltatime){
		
	}
}
