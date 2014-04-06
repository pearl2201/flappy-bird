package com.pearl.main.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.pearl.main.game.Assets;
import com.pearl.main.utils.Constants;


public class Score extends Image{
	
	public static Score instance = new Score();
	private BitmapFont font;
	private int highScore;
	private int score;
	private FileHandle f;
	
	public Score()
	{
		score = 0;
		f = Gdx.files.local(Constants.SCORE_FILE);
		highScore = Integer.parseInt(f.readString());
		
		font = Assets.instance.font.font;		
	}
	
	public void addScore(int score)
	{
		this.score += score;
		System.out.println("The Score is: " + this.score);
	}
	
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	public void setHighScore()
	{
		if ( score > highScore )
		{
			highScore = score;
			f.writeString(Integer.toString(highScore),false);
			System.out.println(highScore);
		}
		
	}
		
	public int getHighScore()
	{
		return highScore;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void resetScore()
	{
		score = 0 ;
		setVisible(true);
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.ui.Image#draw(com.badlogic.gdx.graphics.g2d.Batch, float)
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		
		font.draw(batch, Integer.toString(score), Constants.VIEWPORT_WIDTH/2- font.getBounds(Integer.toString(score)).width/2, Constants.VIEWPORT_HEIGHT);
	}
	
}
