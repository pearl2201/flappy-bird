package com.pearl.main;


import com.badlogic.gdx.assets.AssetManager;
import com.pearl.main.game.Assets;
import com.pearl.main.screen.Director;
import com.pearl.main.screen.MenuScreen;

public class Pearl extends Director {

	@Override
	public void create() {
		// TODO Auto-generated method stub
		Assets.instance.init(new AssetManager());
		 
		setScreen(new MenuScreen(this));
	}
	
}
