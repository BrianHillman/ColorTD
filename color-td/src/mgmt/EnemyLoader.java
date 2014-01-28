package mgmt;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.me.actors.Enemy;




public class EnemyLoader {
	
	private ArrayList<MyEnemyPrototype> enemies;
	
	
	
	public Enemy makeEnemy(String name, Array<Pair> path, float timeTillSpawn){
		MyEnemyPrototype temp = null;
		for(int x = 0; x < enemies.size() ; x++){
			if(enemies.get(x).name.equalsIgnoreCase(name)) temp = enemies.get(x);
		}
		if(temp == null){
			System.out.println("Enemy not found");
			System.exit(0);
		}
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("data/sprites/"+temp.sprite.toLowerCase())));
		Enemy e  = new Enemy(temp.name, temp.maxHealth, temp.speed, sprite, temp.flying, path,timeTillSpawn);
		return e;
	}
}
