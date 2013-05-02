package com.kilobolt.robotgame;

import android.util.Log;

import com.nanofunncool.framework.Game;
import com.nanofunncool.framework.Graphics;
import com.nanofunncool.framework.Graphics.ImageFormat;
import com.nanofunncool.framework.Screen;

public class SplashLoadingScreen extends Screen {
	private final boolean DEBUG = true;
	private final String TAG = "SplashLoadingScreen";
	
	public SplashLoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		if (DEBUG) Log.d(TAG, "update()");
		Graphics g = game.getGraphics();
		Assets.splash= g.newImage("splash.png", ImageFormat.RGB565);
	
		game.setScreen(new LoadingScreen(game));
	}

	@Override
	public void paint(float deltaTime) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}