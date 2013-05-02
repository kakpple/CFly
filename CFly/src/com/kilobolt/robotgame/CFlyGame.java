package com.kilobolt.robotgame;

import android.util.Log;

import com.nanofunncool.framework.Screen;
import com.nanofunncool.framework.implementation.AndroidGame;

public class CFlyGame extends AndroidGame {
	private final  boolean DEBUG = true;
	private final String TAG = "CFlyGame";
	
	private boolean firstTimeCreate = true;

	@Override
	public Screen getInitScreen() {
		if (DEBUG) Log.d(TAG, "getInitScreen()");
		if (firstTimeCreate) {
			Assets.loadThemeSong(this);
			firstTimeCreate = false;
		}
		
		return new SplashLoadingScreen(this);
	}

	@Override
	public void onBackPressed() {
		if (DEBUG) Log.d(TAG, "onBackPressed()");
		getCurrentScreen().backButton();
	}

	@Override
	public void onResume() {
		if (DEBUG) Log.d(TAG, "onResume()");
		super.onResume();
		Assets.theme.play();
	}

	@Override
	public void onPause() {
		if (DEBUG) Log.d(TAG, "onPause()");
		super.onPause();
		Assets.theme.pause();
	}
}