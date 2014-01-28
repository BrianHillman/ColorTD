/**
 * 
 */
package com.me.mainscreens;

import mgmt.EnemyLoader;
import mgmt.Pair;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.me.actors.Enemy;

/**
 * @author Brian
 *
 */
public class GameScreen implements Screen{
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Stage stage;
	
	public GameScreen(){

		SpriteBatch batch = new SpriteBatch();
		stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        loadLevel(10);
	}
	
	public void loadLevel(int x){
		
		Array<Pair> path = new Array<Pair>();
		
		path.add(new Pair(1,1));
		path.add(new Pair(100,100));
		path.add(new Pair(200,100));
		path.add(new Pair(200,380));
		path.add(new Pair(500,380));
		path.add(new Pair(500,100));
		path.add(new Pair(700,100));
		path.add(new Pair(700,380));
		
		stage.clear();
		//just loading a sample level for now.
		
		Json json = new Json();
		
		EnemyLoader loader = json.fromJson(EnemyLoader.class, Gdx.files.internal("data/units.json"));
		for(int ii = 1; ii < 21; ii++){
			Enemy temp = loader.makeEnemy("Goblin", path,10f/20*ii);
			stage.addActor(temp);
		}
		
		
		
	}
	
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
		// update the stage
		stage.act();
		// draw the stage
		stage.draw();
		

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
