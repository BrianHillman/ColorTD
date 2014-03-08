package com.brian.actors;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.brian.util.EnemyLoader;
import com.brian.util.Pair;

public class EnemyWaveLoader{
	//enemy waves read enemy info from a file. 
	private Stage stage;
	public int currentWave = 0;
	private ArrayList<ArrayList<Enemy>> waves;
	Json json = new Json();

	EnemyLoader loader = json.fromJson(EnemyLoader.class, Gdx.files.internal("data/units.json"));

	//we also need a path for the enemies to follow. 
	

	
	
	public ArrayList<Enemy> loadLevel(Array<Pair> path){
		
		//Array<Pair> path = new Array<Pair>();
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		//path.add(new Pair(1,1));
		//path.add(new Pair(100,100));
		//path.add(new Pair(200,100));
	//	path.add(new Pair(200,450));
		//path.add(new Pair(500,450));
	//	path.add(new Pair(500,100));
	//	path.add(new Pair(760,100));
	//	path.add(new Pair(760,480));
		
		//stage.clear();
		//just loading a sample level for now.
		
		
		 
		for(int ii = 0; ii < 10; ii++){
			Enemy temp = loader.makeEnemy("Goblin", path, ii/1f );
			enemies.add(temp);
		}
		return enemies;
		
		
	}
	
	
}
