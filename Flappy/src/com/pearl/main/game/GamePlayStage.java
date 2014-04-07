package com.pearl.main.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pearl.main.entities.Background;
import com.pearl.main.entities.Bird;
import com.pearl.main.entities.GameOver;
import com.pearl.main.entities.Pipes;
import com.pearl.main.entities.Score;
import com.pearl.main.screen.Director;
import com.pearl.main.utils.Constants;

public class GamePlayStage extends Stage {


	private boolean start;
	private Image instruction;
	private Background background;
	private Bird bird;
	private Pipes pipes;
	private boolean finish;
	private GameOver gameOver;
	private boolean gameOverVisible;
	private ClickListener listener;
	
	
	public GamePlayStage(Director game, Viewport viewport, SpriteBatch batch )
	{
		super(viewport, batch);
		// add item
		start = false;
		finish = false;
		gameOverVisible = false;
		Score.instance.resetScore();

		background = new Background();
		instruction = new Image(Assets.instance.menu.instruction);
		bird = new Bird();
		pipes = new Pipes();
		gameOver = new GameOver(game);
		gameOver.setVisible(false);

		addActor(background);
		addActor(instruction);
		
		addActor(pipes);
		addActor(bird);
		addActor(Score.instance);
		addActor(gameOver);


		instruction.setX(Constants.VIEWPORT_WIDTH / 2 - instruction.getWidth()
				/ 2);
		instruction.setY(Constants.VIEWPORT_HEIGHT / 2
				+ Constants.FOOTER_HEIGHT);
		
		// add event click for pipe
		listener = new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				if (!start) {
					start = true;
					instruction.setVisible(false);
					bird.fly();
				} else {
					if (!finish)
					{
						bird.fly();
					}
					else {
						
					}
				}
				return false;
				}
			
		};
		
		addListener(listener);
	}
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Stage#act()
	 */
	@Override
	public void act(float deltaTime) {
		// TODO Auto-generated method stub
		

		// If game is start then update and check pipe collision,
		// if game is finish then check bird go down floor, if bird in floor then set GameOver message.
		if (start) {

			if (!finish) {
				super.act(deltaTime);
				pipes.checkScoreBiro(bird);
				if (pipes.checkCollision(bird) || bird.isGoHell()) {
					finish = true;
					bird.goToHell();
					Score.instance.setHighScore();
					Assets.instance.sounds.hit.play();
				}
			} else {
				if ( !gameOverVisible) {
					super.act(deltaTime);	
					if (bird.isDeath()) {
						gameOverVisible = true;
					}
				}
				else
				{
					super.removeListener(listener);
					gameOver.setVisible(true);
					Score.instance.setVisible(false);
				}
			}

		} else {
			background.act(deltaTime);

		}

	}

}
