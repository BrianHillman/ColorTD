/**
 * 
 */
package com.brian.mainscreens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.brian.actors.Enemy;
import com.brian.util.EnemyLoader;
import com.brian.util.Pair;

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
	
	 public final int VIEWPORT_WIDTH = 800;
	public final int VIEWPORT_HEIGHT = 480;
	public GameScreen(){
		
		this.stage = new Stage( VIEWPORT_WIDTH, VIEWPORT_HEIGHT, true );
        //Gdx.input.setInputProcessor(stage);
        loadLevel(10);
        
        
	}
	
	public void loadLevel(int x){
		
		Array<Pair> path = new Array<Pair>();
		
		path.add(new Pair(1,1));
		path.add(new Pair(100,100));
		path.add(new Pair(200,100));
		path.add(new Pair(200,450));
		path.add(new Pair(500,450));
		path.add(new Pair(500,100));
		path.add(new Pair(760,100));
		path.add(new Pair(760,480));
		
		stage.clear();
		//just loading a sample level for now.
		
		Json json = new Json();
		
		EnemyLoader loader = json.fromJson(EnemyLoader.class, Gdx.files.internal("data/units.json"));
		for(int ii = 1; ii < 2; ii++){
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
		
		if(selected != null){
			shapeRenderer.begin(ShapeType.Line);
			shapeRenderer.setColor(0,1,0,1);
			shapeRenderer.rect(selected.getX()-5, selected.getY()-5, selected.getWidth()+10, selected.getHeight()+10);
			shapeRenderer.end();
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		
		if (button == Input.Buttons.LEFT) {
				Gdx.app.log("touchUp", "X:"+screenX+"\tY:"+screenY );
				
				Vector2 pos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
				stage.screenToStageCoordinates(pos);
				
	          selected = stage.hit(pos.x, pos.y, true);
	          
	          return true;     
	      }
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		//we need to draw generic turret at current location if and only if there are less than 5 placed this round. 
		
		
		return false;
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
	
	
}
