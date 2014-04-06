package com.pearl.main.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pearl.main.entities.Background;
import com.pearl.main.entities.Bird;
import com.pearl.main.entities.GameOver;
import com.pearl.main.entities.Pipes;
import com.pearl.main.entities.Score;
import com.pearl.main.game.Assets;
import com.pearl.main.utils.Constants;

public class GameScreen extends AbstractGameScreen {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;
	private boolean start;
	private Image instruction;
	private Background background;
	private Bird bird;
	private Pipes pipes;
	private boolean finish;
	private GameOver gameOver;
	private boolean gameOverVisible;
	private ClickListener listener;

	public GameScreen(Director game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		// setup world
		start = false;
		camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH,
				Constants.VIEWPORT_HEIGHT);
		camera.position.set(Constants.VIEWPORT_WIDTH / 2,
				Constants.VIEWPORT_HEIGHT / 2, 0);
		camera.update();
		batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport(camera), batch);

		// add item
		finish = false;
		gameOverVisible = false;
		Score.instance.resetScore();

		background = new Background();
		instruction = new Image(Assets.instance.menu.instruction);
		bird = new Bird();
		pipes = new Pipes();
		gameOver = new GameOver(game);
		gameOver.setVisible(false);

		stage.addActor(background);
		stage.addActor(instruction);
		
		stage.addActor(pipes);
		stage.addActor(bird);
		stage.addActor(Score.instance);
		stage.addActor(gameOver);

		
		// add event click for pipe
		listener = new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				if (!start) {
					start = true;
					instruction.setVisible(false);
				} else {
					if (!finish)
						bird.fly();
					else {
						
					}
				}
				return false;
				}
			
		};
		
		stage.addListener(listener);
				

	}

	@Override
	public void render(float deltaTime) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.4f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		// handleInput();
		
		// If game is start then update and check pipe collision,
		// if game is finish then check bird go down floor, if bird in floor then set GameOver message.
		if (start) {

			if (!finish) {
				stage.act(deltaTime);
				pipes.checkScoreBiro(bird);
				if (pipes.checkCollision(bird) || bird.isGoHell()) {
					finish = true;
					bird.goToHell();
					Score.instance.setHighScore();
				}
			} else {
				if ( !gameOverVisible) {
					stage.act(deltaTime);	
					if (bird.isDeath()) {
						gameOverVisible = true;
					}
				}
				else
				{
					stage.removeListener(listener);
					gameOver.setVisible(true);
					Score.instance.setVisible(false);
				}
			}

		} else {
			background.act(deltaTime);

		}
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

		instruction.setX(Constants.VIEWPORT_WIDTH / 2 - instruction.getWidth()
				/ 2);
		instruction.setY(Constants.VIEWPORT_HEIGHT / 2
				+ Constants.FOOTER_HEIGHT);
		stage.getViewport().update(width, height, true);
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
