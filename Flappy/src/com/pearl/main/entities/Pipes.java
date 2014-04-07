package com.pearl.main.entities;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.pearl.main.game.Assets;
import com.pearl.main.utils.Constants;

public class Pipes extends Actor {

	private float speed;

	private static final int maxDeltaY = 100; // the compare max between the
												// head of pipeUp.
	private static final float maxMiddle = 160; // the distance between pipeUp and
											// pipeDown
	private static final float minMiddle = 100;
	private static final int minXPipe = (int) (Constants.FOOTER_HEIGHT + maxMiddle + 20);
	private static final int maxXPipe = (int) (Constants.VIEWPORT_HEIGHT - 20);

	/*
	 * 
	 */
	public class Pipe {

		/*
		 * Chu y:
		 * 
		 * De tien cho tinh toan thi minh se lay position la diem chinh giua cua
		 * mieng nhe
		 */

		private Sprite pipeUp;
		private Sprite pipeDown;
		private float middle;
		private boolean over;
		private float width;

		/**
		 * @return the width
		 */
		public float getWidth() {
			return width;
		}

		/**
		 * @param width
		 *            the width to set
		 */
		public void setWidth(float width) {
			this.width = width;
		}

		private float x,y;

		/**
		 * @return the x
		 */
		public float getX() {
			return pipeUp.getX();
		}

		public float getY()
		{
			return y;
		}

		/**
		 * Over is true when the bird is over it to sure that score not to be add more than twice  
		 */
		public boolean isOver() {
			return over;
		}

		public void setOver(boolean over) {
			this.over = over;
		}

		public Rectangle getMiddleBoundRectangle() {
			return new Rectangle(pipeDown.getX(), pipeDown.getY() - middle,
					pipeDown.getWidth(), middle);
		}

		public Pipe(int x, int y, float middle) {
			this.x = x;
			this.y=y;
			pipeUp = new Sprite(Assets.instance.enemy.pipe, 0, 0,
					Assets.instance.enemy.pipe.getRegionWidth(), (int) (y
							- middle - Constants.FOOTER_HEIGHT));
			width = pipeUp.getWidth();

			pipeDown = new Sprite(Assets.instance.enemy.pipe);
			pipeDown.flip(false, true);
			pipeDown.setX(x);
			pipeDown.setY(y);
			pipeUp.setX(x);
			pipeUp.setY(Constants.FOOTER_HEIGHT);
			this.middle = middle;
			over = false;
		}

		public void draw(Batch batch, float parentAlpha) {

			pipeUp.draw(batch);
			pipeDown.draw(batch);

		}

		public void act(float delta) {

			pipeUp.setX(pipeUp.getX() - speed);

			pipeDown.setX(pipeUp.getX() - speed);

		}

		public Rectangle getPipeUp() {
			return pipeUp.getBoundingRectangle();
		}

		public Rectangle getPipeDown() {
			return pipeDown.getBoundingRectangle();
		}

		public float getPositionX() {
			return pipeUp.getX() + pipeUp.getWidth() / 2;
		}

	}

	/*
	 * 
	 */

	private Array<Pipe> pipes;

	private float middle;

	public Pipes() {
		speed = 1f;
		pipes = new Array<Pipe>();
		pipes.add(new Pipe(280,320,100));
		
		for (int i = 0; i < 3; i++) {
			addPipe();
		}
	}

	public boolean checkCollision(Bird bird) {
		Iterator<Pipe> iterators = pipes.iterator();
		while (iterators.hasNext()) {
			Pipe iterator = iterators.next();
			if (iterator.getPipeDown().overlaps(bird.getBoundingRectangle())
					|| iterator.getPipeUp().overlaps(
							bird.getBoundingRectangle()))
				return true;
		}

		return false;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		Iterator<Pipe> iterators = pipes.iterator();
		while (iterators.hasNext()) {
			Pipe iterator = iterators.next();
			iterator.draw(batch, parentAlpha);
			;
		}
	}

	@Override
	public void act(float delta) {
		
		if (pipes.get(0).getX() < -pipes.get(0).getWidth()) {
			pipes.removeIndex(0);
			addPipe();
			Gdx.app.log("pipe", "add new pipe");
		}
		
		Iterator<Pipe> iterators = pipes.iterator();
		while (iterators.hasNext()) {

			Pipe iterator = iterators.next();

			iterator.act(delta);
			
		}
	}

	public void checkScoreBiro(Bird bird) {
		// TODO Auto-generated method stub
		Iterator<Pipe> iterators = pipes.iterator();
		while (iterators.hasNext()) {
			Pipe iterator = iterators.next();
			if (bird.getBoundingRectangle().overlaps(
					iterator.getMiddleBoundRectangle())
					&& !iterator.isOver()) {
				Score.instance.addScore(1);
				Assets.instance.sounds.point.play();
				iterator.setOver(true);
			}
		}
	}

	private void addPipe()
	{
		int x = (int) (pipes.get(pipes.size - 1).getX() + 180);
		int y = (int) (pipes.get(pipes.size - 1).getY());;
		int sign =0;
		middle = (float) Math.random()*(maxMiddle-minMiddle) + minMiddle;
		sign = MathUtils.randomBoolean()? 1 : -1;
		y = (int) (y + Math.random()*maxDeltaY*sign);
		if ( y >= maxXPipe)
		{
			y = maxXPipe;
		}
		
		if (y<= minXPipe )
		{
			y = (int) minXPipe;
		}
		Gdx.app.log("middle", middle+ "" );
		
		pipes.add(new Pipe(x, y, middle ));
	}
}
