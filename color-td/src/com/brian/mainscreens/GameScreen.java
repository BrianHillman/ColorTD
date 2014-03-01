/**
 * 
 */
package com.brian.mainscreens;


import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.brian.actors.Enemy;
import com.brian.actors.EnemyWaveLoader;
import com.brian.actors.GenericTower;
import com.brian.actors.KeepButton;
import com.brian.actors.UnevolvedTower;
import com.brian.util.EnemyLoader;
import com.brian.util.Pair;
import com.brian.util.TowerLoader;

/**
 * @author Brian
 *
 */




public class GameScreen implements Screen, InputProcessor {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Stage stage;
	Actor selected = null;
	
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	
	private final int GAMESTATEPAUSE = 0;
	private final int GAMESTATEBETWEENROUNDS = 1;
	private final int GAMESTATEROUND = 2;
	
	public int gameState = 1;
	
	public final int VIEWPORT_WIDTH = 400;
	public final int VIEWPORT_HEIGHT = 280;
	
	public GenericTower[] towersBeingPlaced = new GenericTower[5];
	
	Json json = new Json();
	EnemyLoader loader = json.fromJson(EnemyLoader.class, Gdx.files.internal("data/units.json"));
	
	TowerLoader towerLoader = json.fromJson(TowerLoader.class, Gdx.files.internal("data/towers.json"));

	
	private FPSLogger fpsLogger;
	
	KeepButton keepButton = new KeepButton();
	EnemyWaveLoader enemyWaveLoader = new EnemyWaveLoader();

	public GameScreen(){
		this.stage = new Stage( VIEWPORT_WIDTH, VIEWPORT_HEIGHT, true );
        Gdx.input.setInputProcessor(stage);
        fpsLogger = new FPSLogger();
		Json json = new Json();

		
		
	
        
        
   
        createUnevolvedTowers();
        
        stage.addActor(keepButton);
	}
	
	public void loadLevel(int x){
		
		Array<Pair> path = new Array<Pair>();
		
		path.add(new Pair(1,1));
		path.add(new Pair(100,50));
		path.add(new Pair(150,50));
		path.add(new Pair(150,225));
		path.add(new Pair(300,225));
		path.add(new Pair(300,50));
		path.add(new Pair(375,50));
		path.add(new Pair(375,280));
		
		//stage.clear();
		//just loading a sample level for now.
		
		 ArrayList<Enemy> enemyWave = enemyWaveLoader.loadLevel(path);

		 
		for(int ii = 0; ii < enemyWave.size(); ii++){
			
			stage.addActor(enemyWave.get(ii));
		}
		
		
		
	}
	
	
	
	@Override
	public void render(float delta) {
	
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// update the stage
        if(gameState != GAMESTATEPAUSE)
        	stage.act();
        
		// draw the stage
		stage.draw();
		fpsLogger.log();
		
		
		boolean enemiesAlive = false;
		for(int x = 0; x < stage.getActors().size; x++){
			if(stage.getActors().get(x) instanceof Enemy) enemiesAlive = true;
		}
		
		if(!enemiesAlive && GAMESTATEROUND == gameState){
			gameState = GAMESTATEBETWEENROUNDS;
			createUnevolvedTowers();
			
		}
		
		

	}
	
	//if game's over open victory screen 
	//else spawn 5 towers for them
	public void endRound(){
		
		//TODO fix this lazy shit
		
		
		
	}
	public void createUnevolvedTowers(){
		Gdx.app.log("creating towers", "" );
		int[] towerXYs = {	440,150,
							420,150,
							420, 130,
							440,130,
							430,110};
		
		
		for( int x = 0; x < towersBeingPlaced.length; x++){
				Gdx.app.log("created tower", "" );
				UnevolvedTower temp = towerLoader.makeUnevolvedTower(towerXYs[x*2], towerXYs[x*2+1]);
				towersBeingPlaced[x] = temp;
				stage.addActor(temp);
				Gdx.app.log("num actors", ""+stage.getActors().size );
			}
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Actor temp = selected;

		
		
		Vector2 stageCoords = new Vector2();
		stage.screenToStageCoordinates(stageCoords.set(screenX,screenY));
		

		temp = stage.hit(stageCoords.x, stageCoords.y, true);
		
		if( temp != null){
			
			if(selected instanceof Enemy){
				((Enemy) selected).isTouched = false;
				
			}else if(selected instanceof GenericTower){
				
				((GenericTower) selected).isTouched = false;
				
			}
			
			if(temp instanceof KeepButton && selected instanceof UnevolvedTower){
				upgrade((UnevolvedTower) selected);
			}
			selected = temp;
			
		}
		
		
		
		return stage.touchDown(screenX, screenY, pointer, button);
		
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		
		return stage.touchUp(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		
		return stage.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void upgrade(UnevolvedTower tower){
		System.out.println("Upgrading");
		for(int x = 0; x < towersBeingPlaced.length; x++){
			if(tower != towersBeingPlaced[x] ){
				
				stage.addActor(towerLoader.makeTower("wall", (int) towersBeingPlaced[x].getX(), (int) towersBeingPlaced[x].getY()));
				towersBeingPlaced[x].remove();
				towersBeingPlaced[x] = null;
				
			}else{
				//TODO update tower evolving
				
				GenericTower newTower = towerLoader.makeTower("Green 1", (int) towersBeingPlaced[x].getX(), (int) towersBeingPlaced[x].getY());
				stage.addActor(newTower);
				towersBeingPlaced[x].remove();
				towersBeingPlaced[x] = null;
			}
		}
		gameState = GAMESTATEROUND;
		loadLevel(10);
	}
	
	public void UI(){
		//combine button. 
		
		//keep tower button.
		
		
		
		
	}

}
