package com.pearl.main.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pearl.main.game.Assets;

public class Background extends Actor{
	
	private Sprite bgS;;
	private Sprite footerS;
	private float positionX;
	private float speed;
	
	public Background()
	{
		bgS = new Sprite(Assets.instance.background.background);
		
		footerS = new Sprite(Assets.instance.background.footer);
		
		speed = 0.4f;
		positionX = 0f;
		
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		
		for (int i =0; i<2; i++)
		{
			
			batch.draw(bgS, positionX + bgS.getWidth()*i,0);
			batch.draw(footerS,positionX + footerS.getWidth()* i,0);
		}
	}
	

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		positionX -= speed;
		if (positionX < - bgS.getWidth())
		{
			positionX = 0;
		}
		
		
	}

}
