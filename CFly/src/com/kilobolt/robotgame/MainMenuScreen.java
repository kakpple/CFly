package com.kilobolt.robotgame;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.nanofunncool.framework.Game;
import com.nanofunncool.framework.Graphics;
import com.nanofunncool.framework.Input.TouchEvent;
import com.nanofunncool.framework.Screen;

public class MainMenuScreen extends Screen {
	private final boolean DEBUG = false;
	private final String TAG = "MainMenuScreen";
	private Paint paint;
	
	public MainMenuScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		if (DEBUG) Log.d(TAG, "update()");
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 480, 800)) {
					game.setScreen(new GameScreen(game));
				}
			}
		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.menu, 0, 0);
		
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 240, 400, paint);
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
        android.os.Process.killProcess(android.os.Process.myPid());

	}
}
