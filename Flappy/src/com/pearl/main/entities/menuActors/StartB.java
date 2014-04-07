package com.pearl.main.entities.menuActors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.pearl.main.game.Assets;
import com.pearl.main.screen.Director;
import com.pearl.main.screen.MenuScreen;
import com.pearl.main.screen.transition.ScreenTransition;
import com.pearl.main.screen.transition.ScreenTransitionSlide;
import com.pearl.main.utils.Constants;

public class StartB extends Actor {
	
	private Sprite startI;
	private Director game;
	
	public StartB(Director game) {
		this.game = game;
		startI = new Sprite(Assets.instance.icon.start);

		startI.setY(Constants.FOOTER_HEIGHT + 20);
		startI.setX(Constants.VIEWPORT_WIDTH / 4 - startI.getWidth() / 2);
		setBounds(startI.getX(), startI.getY(), startI.getWidth(),
				startI.getHeight());
		setEvent();

		
	}
	
	private void setEvent()
	{
		addListener(new ClickListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.badlogic.gdx.scenes.scene2d.utils.ClickListener#touchDown
			 * (com.badlogic.gdx.scenes.scene2d.InputEvent, float, float, int,
			 * int)
			 */
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				ScreenTransition transition = ScreenTransitionSlide
						.init(2f, ScreenTransitionSlide.LEFT, true,
								Interpolation.exp5Out);

				game.setScreen(new MenuScreen(game), transition);

				return false;
			}

		});
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.scenes.scene2d.Actor#draw(com.badlogic.gdx.graphics.g2d.Batch, float)
	 */
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub

		startI.draw(batch, parentAlpha);
	}
}
