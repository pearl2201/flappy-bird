package com.pearl.main.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pearl.main.game.Assets;

public class Background extends Actor{
	
	private Sprite bg;;
	private Sprite footer;
	private float bgPositionX;
	private float footerPositionX;
	private float bgSpeed;
	private float footerSpeed;
	
	public Background()
	{
		bg = new Sprite(Assets.instance.background.background);
		
		footer = new Sprite(Assets.instance.background.footer);
		
		bgSpeed = 0.4f;
		footerSpeed = 0.4f;
		bgPositionX = 0f;
		footerPositionX = 0f;
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		
		for (int i =0; i<2; i++)
		{
			
			batch.draw(bg, bgPositionX + bg.getWidth()*i,0);
						batch.draw(footer,footerPositionX + footer.getWidth()* i,0);
		}
	}
	

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		bgPositionX -= bgSpeed;
		if (bgPositionX < - bg.getWidth())
		{
			bgPositionX = 0;
		}
		
		footerPositionX -= footerSpeed;
		
		if (footerPositionX < -footer.getWidth())
		{
			footerPositionX = 0;
		}
	}

}
