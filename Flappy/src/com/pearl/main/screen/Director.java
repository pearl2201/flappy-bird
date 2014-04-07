package com.pearl.main.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.pearl.main.screen.transition.ScreenTransition;


/*
 * 
 * Director help setScreen, changeScreen
 * 
 * 
 */
public abstract class Director implements ApplicationListener {

	private AbstractGameScreen currentScreen;
	private AbstractGameScreen nextScreen;
	private FrameBuffer currentFbo;
	private FrameBuffer nextFbo;
	private boolean init;
	private ScreenTransition transition;
	private SpriteBatch batch;
	private float t;
	private float duration;

	public void setScreen(AbstractGameScreen screen) {
		setScreen(screen, null);
	}

	public void setScreen(AbstractGameScreen screen, ScreenTransition transition) {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		nextScreen = screen;
		if (!init) {
			currentFbo = new FrameBuffer(Format.RGBA8888, w, h, false);
			nextFbo = new FrameBuffer(Format.RGBA8888, w, h, false);
			init = true;
			batch = new SpriteBatch();
			
			
		}
		nextScreen.show();
		nextScreen.render(0);
		nextScreen.resize(w, h);
		nextScreen.pause();

		if (currentScreen != null)
			currentScreen.pause();

		Gdx.input.setInputProcessor(null);

		this.transition = transition;

		t = 0;

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		if (currentScreen!=null) currentScreen.resize(width, height);
		if (nextScreen!=null) nextScreen.resize(width, height);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		float deltaTime = Math.min(Gdx.graphics.getDeltaTime(), 1f / 60f);
		if (nextScreen == null) {
			currentScreen.render(deltaTime);

		} else {
			duration = 0;

			if (transition != null)
				duration = transition.getDuration();

			t = Math.min(t + deltaTime, duration);

			if (transition == null || t >= duration) {
				if (currentScreen != null)
					currentScreen.hide();

				currentScreen = nextScreen;
				currentScreen.resume();

				Gdx.input.setInputProcessor(nextScreen.getInputProcessor());

				

				nextScreen = null;

				t = 0;

				transition = null;
			}

			else

			{
				currentFbo.begin();
				if (currentScreen != null)
					currentScreen.render(deltaTime);
				currentFbo.end();
				nextFbo.begin();
				nextScreen.render(deltaTime);
				nextFbo.end();
				float alpha = t / duration;
				transition.render(batch, 
						currentFbo.getColorBufferTexture(),
						nextFbo.getColorBufferTexture(), alpha);

			}

		}

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if (currentScreen!=null) currentScreen.pause();
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		if (currentScreen!=null) currentScreen.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		if (currentScreen != null)
			currentScreen.hide();
		if (nextScreen != null)
			nextScreen.hide();
		if (init) {
			if (currentFbo != null)
				currentFbo.dispose();
			if (nextFbo != null)
				nextFbo.dispose();
			currentScreen = null;
			nextScreen = null;
			batch.dispose();
		}
	}

}
