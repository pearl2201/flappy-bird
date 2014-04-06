package com.pearl.main.game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.pearl.main.utils.Constants;

public class Assets implements Disposable, AssetErrorListener{
	public static Assets instance = new Assets();
	private AssetManager assetManager;
	
	
	
	public AssetsEnemy enemy;
	public AssetsPlayer player;
	public AssetsBackground background;
	public AssetsGameOver gameover;
	public AssetsIcon icon;
	public AssetsMenu menu;
	public AssetsFont font;
	public AssetsSound sounds;
	
	
	
	/*
	 * Load Assets Component
	 */
	

	public class AssetsSound
	{
		public Sound hit;
		public Sound swing;
		public Sound point;
		public AssetsSound(AssetManager am)
		{
			hit = am.get("sound/sfx_hit.mp3", Sound.class);
			point = am.get("sound/sfx_point.mp3", Sound.class);
			swing = am.get("sound/sfx_wing.mp3", Sound.class);
		}
		
	}
	
	
	public class AssetsEnemy{
		
		public TextureRegion pipen;
		public TextureRegion pipe;
		
		public AssetsEnemy(TextureAtlas atlas)
		{
			pipe = atlas.findRegion("pipe");
		}
	}
	
	public class AssetsPlayer{
		
		private float duration;
		public Animation animation;
		public TextureRegion bird;
		
		public AssetsPlayer(TextureAtlas atlas)
		{
			duration = 0.05f;
			bird = atlas.findRegion("bird1"); 
			Array<AtlasRegion> regions = new Array<AtlasRegion>(); 
			
			for (int i = 1; i<4; i++)
			{
				regions.add(atlas.findRegion("bird"+Integer.toString(i)));
			}

			animation = new Animation(duration, regions);
		}
		
	}
	
	
	public class AssetsIcon{
		
		public TextureRegion start;
		public TextureRegion score;
		public TextureRegion newI;
		
		
		public AssetsIcon(TextureAtlas atlas)
		{
			start = atlas.findRegion("start");
			score = atlas.findRegion("step");
			newI = atlas.findRegion("new");
		}
	}
	
	public class AssetsMenu{
		
		public TextureRegion ready;
		public TextureRegion instruction;
		public TextureRegion logo;
		
		public AssetsMenu(TextureAtlas atlas)
		{
			ready = atlas.findRegion("ready");
			instruction = atlas.findRegion("instruction");
			logo = atlas.findRegion("flappy");
		}
		
	}
	
	public class AssetsGameOver{
		
		public TextureRegion award;
		public TextureRegion coin_white_light;
		public TextureRegion coin_white_dark;
		public TextureRegion coin_yellow_light;
		public TextureRegion coin_yellow_dark;
		public TextureRegion game_over;
		public Array<Sprite> medals;
		
		public AssetsGameOver(TextureAtlas atlas)
		{
			award = atlas.findRegion("award");
			coin_white_light = atlas.findRegion("coin_white_light");
			coin_white_dark = atlas.findRegion("coin_white_dark");
			coin_yellow_light = atlas.findRegion("coin_yellow_light");
			coin_yellow_dark = atlas.findRegion("coin_yellow_dark");
			medals = new Array<Sprite>();
			medals.add(new Sprite(coin_white_light));
			medals.add(new Sprite(coin_white_dark));
			medals.add(new Sprite(coin_yellow_light));
			medals.add(new Sprite(coin_yellow_dark));
			game_over = atlas.findRegion("game_over");
		}
	}
	
	public class AssetsBackground{
		
		public AtlasRegion background;
		public TextureRegion footer;
		
		
		public AssetsBackground(TextureAtlas atlas)
		{
			background = atlas.findRegion("background");
			footer = atlas.findRegion("footer1"); 
		}
	}

	public class AssetsFont{
		
		public BitmapFont font;
		public BitmapFont fontScore;
		public AssetsFont(AssetManager am)
		{
			font = am.get(Constants.FONT_PACK,BitmapFont.class);
			fontScore = am.get(Constants.FONT_SCORE_PACK, BitmapFont.class);
			
			
		}
	}
	
	
	public void init(AssetManager am )
	{
		this.assetManager = am;
		
		assetManager.load(Constants.FLAPPY_TEXTURE_PACK, TextureAtlas.class);	
		assetManager.load(Constants.FONT_PACK, BitmapFont.class);
		assetManager.load(Constants.FONT_SCORE_PACK, BitmapFont.class);
		assetManager.load("sound/sfx_hit.mp3", Sound.class);
		assetManager.load("sound/sfx_point.mp3", Sound.class);
		assetManager.load("sound/sfx_wing.mp3", Sound.class);
		
		
		assetManager.finishLoading();
		
		TextureAtlas atlas = assetManager.get(Constants.FLAPPY_TEXTURE_PACK);
		
		for (Texture texture : atlas.getTextures())
		{
			texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		background = new AssetsBackground(atlas);
		player = new AssetsPlayer(atlas);
		icon = new AssetsIcon(atlas);
		enemy = new AssetsEnemy(atlas);
		menu = new AssetsMenu(atlas);
		gameover = new AssetsGameOver(atlas);
		font = new AssetsFont(assetManager);
		sounds = new AssetsSound(assetManager);
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		sounds.hit.dispose();
		sounds.point.dispose();
		sounds.swing.dispose();
		font.font.dispose();
		assetManager.dispose();
	}

}
