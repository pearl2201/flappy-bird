package com.pearl.main.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pearl.main.entities.Background;
import com.pearl.main.game.Assets;
import com.pearl.main.screen.transition.ScreenTransition;
import com.pearl.main.screen.transition.ScreenTransitionSlide;
import com.pearl.main.utils.Constants;

public class MenuScreen extends AbstractGameScreen{

	private Stage stage;
	SpriteBatch batch;
	OrthographicCamera camera;
	
	
	public MenuScreen(Director game) {
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
		Gdx.input.setInputProcessor(stage);
		stage.addActor(new Background());
		
		
		
		
		Image logo = new Image(Assets.instance.menu.logo);
		Image start = new Image(Assets.instance.icon.start);
		Image score = new Image(Assets.instance.icon.score);
		Image bird = new Image(Assets.instance.player.bird);
		start.addListener(new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				ScreenTransition transition = ScreenTransitionSlide.init(2f, ScreenTransitionSlide.LEFT, true, Interpolation.exp5Out);
					game.setScreen(new GameScreen(game),transition);
				return false;
			}
			
		});
		
		score.addListener(new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				ScreenTransition transition = ScreenTransitionSlide.init(2f, ScreenTransitionSlide.LEFT, true, Interpolation.exp5Out);
					game.setScreen(new HighScoreScreen(game));
				return false;
			}
			
		});
		
		start.setX(Constants.VIEWPORT_WIDTH/4 - start.getWidth()/2);
		start.setY(Assets.instance.background.footer.getRegionHeight() + 10);
		
		logo.setX(Constants.VIEWPORT_WIDTH/2 - logo.getWidth()/2);
		logo.setY(Constants.VIEWPORT_HEIGHT*2/3 - logo.getHeight()/2);
		
		score.setX(Constants.VIEWPORT_WIDTH/4*3 - score.getWidth()/2);
		score.setY(Assets.instance.background.footer.getRegionHeight() + 10 );
		
		bird.setX(Constants.VIEWPORT_WIDTH/2);
		bird.setY(Constants.VIEWPORT_HEIGHT*3/4);
		
		stage.addActor(start);
		stage.addActor(logo);
		stage.addActor(score);
		stage.addActor(bird);
		
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
