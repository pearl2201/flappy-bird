package com.pearl.main.entities;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pearl.main.game.Assets;
import com.pearl.main.utils.Constants;

public class Bird extends Actor{
	
	
	private Animation bird;
	private float stateTime;
	private float rotation; 
	private Vector2 origin;
	private float width;
	private float height;
	private float positionX;
	private float positionY;
	private float speed;
	private float acc;
	private float accRotation;
	private boolean goHell;
	
	public Bird()
	{
		bird = Assets.instance.player.animation;
		bird.setPlayMode(Animation.PlayMode.LOOP);
		stateTime = 0;
		rotation =0;
		width = bird.getKeyFrame(0).getRegionWidth();
		height = bird.getKeyFrame(0).getRegionHeight();
		origin = new Vector2(width/2, height/2);
		positionX = Constants.VIEWPORT_WIDTH/5 - origin.x;
		positionY = Constants.VIEWPORT_HEIGHT/2 + Constants.FOOTER_HEIGHT - origin.y;
		speed = 1f;
		acc = - 0.25f;
		accRotation = -2.5f;
		goHell = false;
	}
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub

		stateTime +=delta;
		speed += acc;
		positionY += speed;
		rotation += accRotation;
		
		if ( rotation <= - 90 )
		{
			rotation = -90;
		}
		
		if (positionY + origin.x >=Constants.VIEWPORT_HEIGHT )
		{
			positionY = Constants.VIEWPORT_HEIGHT - origin.x;
			speed = -3;
			rotation -=60;
		}
		
		if (positionY - origin.x<= Constants.FOOTER_HEIGHT)
		{
			goHell = true;
		}
	}

	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub	
		batch.draw(bird.getKeyFrame(stateTime), positionX, positionY, origin.x , origin.y, width, height, 1, 1, rotation);
	}

	public void fly()
	{

		Assets.instance.sounds.swing.play();
		speed = 5f;
		rotation = 60;
	}

	
	
	public void goToHell()
	{
		speed = -10;
		rotation = -90f;
	}
	
	
	// Bird has in hell, hoho
	public boolean isDeath()
	{
		if (positionY -origin.x <= Constants.FOOTER_HEIGHT)
			return true;
		return false; 
	}

	public Rectangle getBoundingRectangle()
	{
		return new Rectangle( positionX, positionY, width,height);
	}
	
	public boolean isGoHell()
	{
		return goHell;
	}

}
