package com.kilobolt.robotgame;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.nanofunncool.framework.Game;
import com.nanofunncool.framework.Graphics;
import com.nanofunncool.framework.Image;
import com.nanofunncool.framework.Input.TouchEvent;
import com.nanofunncool.framework.Screen;

public class GameScreen extends Screen {
	private final boolean DEBUG = true;
	private final String TAG = "GameScreen";
	
	private enum GameState {
		Ready, Running, Paused, GameOver
	}
	private GameState state = GameState.Ready;

	// Variable Setup
	private static Background bg1;
	private static Background bg2;
	private static Robot robot;
	public static Heliboy hb;
	public static Heliboy hb2;

	private Image currentSprite; 
	private Image character;
	private Image character2; 
	private Image character3; 
	private Image heliboy;
	private Image heliboy2;
	private Image heliboy3;
	private Image heliboy4; 
	private Image heliboy5;
	private Animation anim;
	private Animation hanim;

	private int livesLeft = 1;
	private Paint paint;
	private Paint paint2;

	public GameScreen(Game game) {
		super(game);
		if (DEBUG) Log.d(TAG, "GameScreen()");
		
		// Initialize game objects here
		bg1 = new Background(0, 0);
		bg2 = new Background(0, -2160);
		robot = new Robot();
		hb = new Heliboy(100, 150);
		hb2 = new Heliboy(200, 150);

		character = Assets.character;
		character2 = Assets.character2;
		character3 = Assets.character3;

		heliboy = Assets.heliboy;
		heliboy2 = Assets.heliboy2;
		heliboy3 = Assets.heliboy3;
		heliboy4 = Assets.heliboy4;
		heliboy5 = Assets.heliboy5;

		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);

		hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy5, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);

		currentSprite = anim.getImage();

		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}
	
	// kakpple test, shootingInterval
	int shootingInterval = 0;
	
	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		// This is identical to the update() method from our Unit 2/3 game.
		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			/*
			if (event.type == TouchEvent.TOUCH_DOWN) {					
				if (event.x > 240) {
					// Move right.
					robot.moveRight();
					robot.setMovingRight(true);
				} else if (event.x <= 240) {
					robot.moveLeft();
					robot.setMovingLeft(true);
				}
			}

			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 0, 0, 35, 35)) {
					pause();
				}

				if (event.x > 240) {
					robot.stopRight();
				} else if (event.x <=240) {
					robot.stopLeft();
				} else {
					if (DEBUG) Log.d(TAG, "neither event.x>240 nor event.x<=240");
				}
			}
		*/
			if (event.type == TouchEvent.TOUCH_DRAGGED) {					
				if (event.x > robot.getCenterX() + robot.MOVE_SPEED_X) {
					robot.moveRight();
				} else if (event.x <= robot.getCenterX() - robot.MOVE_SPEED_X) {
					robot.moveLeft();
				}
			}

			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 35, 35)) {
					pause();
				}
				robot.stop();
			}
		}

		// 2. Check miscellaneous events like death:

		if (livesLeft == 0) {
			state = GameState.GameOver;
		}

		// 3. Call individual update() methods here.
		// This is where all the game updates happen.
		// For example, robot.update();
		shootingInterval ++;
		if (shootingInterval % 7 == 0){
			robot.shoot();
			shootingInterval = 0;
		}
		robot.update();
		currentSprite = anim.getImage();

		ArrayList projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			if (p.isVisible() == true) {
				p.update();
			} else {
				projectiles.remove(i);
			}
		}

		hb.update();
		hb2.update();
		bg1.update();
		bg2.update();
		animate();

	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 480)) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
		g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());

		ArrayList projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.drawRect(p.getX(), p.getY(), 5, 10, Color.YELLOW);
		}
		// First draw the game elements.

		g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63);
		g.drawImage(hanim.getImage(), hb.getCenterX() - 48, hb.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb2.getCenterX() - 48, hb2.getCenterY() - 48);

		// Example:
		//g.drawImage(Assets.background, 0, 0);
		//g.drawImage(Assets.character, characterX, characterY);

		// Secondly, draw the UI above the game elements.
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

	}

	public void animate() {
		anim.update(10);
		hanim.update(50);
	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		paint = null;
		//bg1 = null;
		//bg2 = null;
		robot = null;
		hb = null;
		hb2 = null;
		currentSprite = null;
		character = null;
		character2 = null;
		character3 = null;
		heliboy = null;
		heliboy2 = null;
		heliboy3 = null;
		heliboy4 = null;
		heliboy5 = null;
		anim = null;
		hanim = null;

		// Call garbage collector to clean up memory.
		System.gc();

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 400, 240, paint);

	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		//g.drawImage(Assets.button, 0, 285, 0, 0, 65, 65);
		//g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65);
		//g.drawImage(Assets.button, 0, 415, 0, 130, 65, 65);
		//g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);

	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 165,400, paint2);
		g.drawString("Menu", 360, 400, paint2);

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawString("GAME OVER.", 400, 240, paint2);
		g.drawString("Tap to return.", 400, 290, paint);

	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;
	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		game.setScreen(new MainMenuScreen(game));

	}

	public static Background getBg1() {
		// TODO Auto-generated method stub
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static Robot getRobot() {
		return robot;
	}

}