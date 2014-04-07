package com.pearl.main.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.pearl.main.entities.Background;
import com.pearl.main.entities.ScoreBoard;
import com.pearl.main.entities.menuActors.StartB;
import com.pearl.main.utils.Constants;

public class HighScoreScreen extends AbstractGameScreen {

	private Stage stage;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	
	public HighScoreScreen(Director game) {
		super(game);
		init();
	}
	
	private void init()
	{
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		camera.position.set(Constants.VIEWPORT_WIDTH/2, Constants.VIEWPORT_HEIGHT/2,0);
		camera.update();
		batch = new SpriteBatch();
		
		stage = new Stage(new FitViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT,camera),batch);
		
		stage.addActor(new Background());
		stage.addActor(new ScoreBoard());
		stage.addActor(new StartB(game));
		
				
	}
	
	public void render(float deltaTime)
	{
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		stage.act(deltaTime);
		stage.draw();
	}

	
	public void show()
	{
		
	}
	
	public void hide()
	{
		stage.dispose();
	}
	
	public void pause()
	{
		
	}
	
	
	public InputProcessor getInputProcessor()
	{
		return stage;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

		
		stage.getViewport().update(width, height, false);
	}

	

	
}
