package com.brian.util;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.brian.actors.Enemy;

class Waves{
	MyEnemyPrototype[][] waves;
	
}

public class EnemyWaveLoader{
	//enemy waves read enemy info from a file. 
	private Stage stage;
	public int currentWave = 0;
	Json json = new Json();

	EnemyLoader loader = json.fromJson(EnemyLoader.class, Gdx.files.internal("data/units.json"));
	//Waves waves = json.fromJson(Waves.class, Gdx.files.internal("data/levelData/level1.json")) ;
	Array<Array<MyEnemyPrototype>> waves = json.fromJson(null, Gdx.files.internal("data/levelData/level1.json"));
	//we also need a path for the enemies to follow. 
	
	
	
	
	public ArrayList<Enemy> loadLevel(Array<Pair> path){
		
		//Array<Pair> path = new Array<Pair>();
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		//stage.clear();
		//just loading a sample level for now.
		for(int ii = 0; ii < waves.get(currentWave).size; ii++){
			MyEnemyPrototype x = waves.get(currentWave).get(ii);
			Enemy temp = loader.makeEnemy(x.name, path, x.spawnTime );
			enemies.add(temp);
		}
		currentWave++;
		return enemies;
	}
	
	
}
