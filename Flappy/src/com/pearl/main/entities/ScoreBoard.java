package com.pearl.main.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.pearl.main.game.Assets;
import com.pearl.main.screen.Director;
import com.pearl.main.screen.MenuScreen;
import com.pearl.main.screen.transition.ScreenTransition;
import com.pearl.main.screen.transition.ScreenTransitionSlide;
import com.pearl.main.utils.Constants;

public class ScoreBoard extends Actor {

	private int score;
	private int highScore;
	private Array<Sprite> medals;
	private Sprite gameOverS;
	private Sprite awardS;
	private Sprite medalS;
	private Director game;
	private float positionY;
	private BitmapFont font;

	public ScoreBoard(Director game) {
		init();
		this.game = game;
	}

	private void init() {
		
		/*
		 * init
		 */
		gameOverS = new Sprite(Assets.instance.gameover.game_over);
		awardS = new Sprite(Assets.instance.gameover.award);
		medals = Assets.instance.gameover.medals;
		font = Assets.instance.font.fontScore; 
		highScore = 0;
		score = 0;
		/*
		 * Khoi tao gia tri
		 */
		positionY = Constants.VIEWPORT_HEIGHT / 2 + Constants.FOOTER_HEIGHT / 2;
		gameOverS.setX(Constants.VIEWPORT_WIDTH / 2 - gameOverS.getWidth() / 2);
		gameOverS.setY(positionY + 160);
		awardS.setX(Constants.VIEWPORT_WIDTH / 2 - awardS.getWidth() / 2);
		awardS.setY(positionY);
		setMedal();
		
		
		//setBounds(0, 0, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
		
	}

	/*
	 * Neu visible thi Game da ket thuc, vi vay cap nhat sprite cho award
	 */
	@Override
	public void setVisible(boolean visible) {
		// TODO Auto-generated method stub
		super.setVisible(visible);
		if (visible) {
			setMedal();
		}
		
	}

	public void setMedal()
	{
		
		score = Score.instance.getScore();
		highScore = Score.instance.getHighScore();
		
			if (score <= 5) {
				medalS = medals.get(0);
			} else if (score <= 10) {
				medalS = medals.get(1);
			}

			else if (score <= 15) {
				medalS = medals.get(2);
			} else {
				medalS = medals.get(3);
			}
			
			medalS.setY(positionY + 30);
			medalS.setX(Constants.VIEWPORT_WIDTH / 2 - 85);
		
	}
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		gameOverS.draw(batch);
		awardS.draw(batch);
		medalS.draw(batch);
		font.draw(batch, Integer.toString(score), Constants.VIEWPORT_WIDTH/2 + 60, positionY + 80);

		font.draw(batch, Integer.toString(highScore), Constants.VIEWPORT_WIDTH/2 + 60, positionY + 40);
	}

}
