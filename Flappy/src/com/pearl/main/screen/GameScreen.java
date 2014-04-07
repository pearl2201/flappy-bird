package com.pearl.main.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.pearl.main.game.GamePlayStage;
import com.pearl.main.utils.Constants;

public class GameScreen extends AbstractGameScreen {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;

	public GameScreen(Director game) {
		super(game);
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init()
	{

		// setup world
		
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
				Constants.VIEWPORT_HEIGHT);
		camera.position.set(Constants.VIEWPORT_WIDTH / 2,
				Constants.VIEWPORT_HEIGHT / 2, 0);
		camera.update();
		batch = new SpriteBatch();
		stage = new GamePlayStage(game,new FitViewport(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT,camera),batch);

	}

	@Override
	public void render(float deltaTime) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		
		stage.act(deltaTime);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

		stage.getViewport().update(width, height, false);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public InputProcessor getInputProcessor() {
		// TODO Auto-generated method stub
		
		return stage;
	}

}
