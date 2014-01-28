package com.me.mainscreens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MainMenuScreen implements Screen {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Stage stage;
	
	public MainMenuScreen(){		
		stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	

	@Override
	public void resize(int width, int height) {
		
        stage.setViewport(width, height, true);
        
        Table table = new Table();
        table.setFillParent(true);
        
        
        Skin uiskin  = new Skin(Gdx.files.internal("data/ui/uiskin.json"));
        
        
        TextButtonStyle buttonStyle = uiskin.get("default",TextButtonStyle.class);
        
        TextButton play = new TextButton("Text Button",uiskin );
        play.setPosition(100, 100);
        
        table.addActor(play);
        stage.addActor(table);
     
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
        
        Table.drawDebug(stage); //renders debug borders for tables
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
}
