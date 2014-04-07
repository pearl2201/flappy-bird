package com.pearl.main;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Interpolation;
import com.pearl.main.game.Assets;
import com.pearl.main.screen.Director;
import com.pearl.main.screen.MenuScreen;
import com.pearl.main.screen.transition.ScreenTransition;
import com.pearl.main.screen.transition.ScreenTransitionSlide;

public class Pearl extends Director {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Assets.instance.init(new AssetManager());
		ScreenTransition transition = ScreenTransitionSlide.init(2f, ScreenTransitionSlide.LEFT, true, Interpolation.exp5Out);
		setScreen(new MenuScreen(this), transition);
	}
	
}
