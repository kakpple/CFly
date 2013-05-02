package com.kilobolt.robotgame;

import java.util.ArrayList;

import android.graphics.Rect;

public class Robot {

	// Constants are Here
	final int MOVE_SPEED_X = 5;
	final int MOVE_SPEED_Y = 5;

	private int centerX = 180;
	private int centerY = 700;
	/*
	private boolean movingLeft = false;
	private boolean movingRight = false;
*/
	private int speedX = 0;
	private int speedY = 0;

	public static Rect rect = new Rect(0, 0, 0, 0);
	public static Rect rect2 = new Rect(0, 0, 0, 0);
	public static Rect rect3 = new Rect(0, 0, 0, 0);
	public static Rect rect4 = new Rect(0, 0, 0, 0);
	public static Rect yellowRed = new Rect(0, 0, 0, 0);

	public static Rect footleft = new Rect(0, 0, 0, 0);
	public static Rect footright = new Rect(0, 0, 0, 0);

	private Background bg1 = GameScreen.getBg1();
	private Background bg2 = GameScreen.getBg2();

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public void update() {
		// Moves Character or Scrolls Background accordingly.
		/*
		 * if (speedX < 0) { centerX += speedX; } if (speedX == 0 || speedX < 0)
		 * { bg1.setSpeedX(0); bg2.setSpeedX(0);
		 * 
		 * } if (centerX <= 200 && speedX > 0) { centerX += speedX; } if (speedX
		 * > 0 && centerX > 200) { bg1.setSpeedX(-MOVESPEED / 5);
		 * bg2.setSpeedX(-MOVESPEED / 5); }
		 * 
		 * // Prevents going beyond X coordinate of 0 if (centerX + speedX <=
		 * 60) { centerX = 61; }
		 * 
		 * rect.set(centerX - 34, centerY - 63, centerX + 34, centerY);
		 * rect2.set(rect.left, rect.top + 63, rect.left+68, rect.top + 128);
		 * rect3.set(rect.left - 26, rect.top+32, rect.left, rect.top+52);
		 * rect4.set(rect.left + 68, rect.top+32, rect.left+94, rect.top+52);
		 * yellowRed.set(centerX - 110, centerY - 110, centerX + 70, centerY +
		 * 70); footleft.set(centerX - 50, centerY + 20, centerX, centerY + 35);
		 * footright.set(centerX, centerY + 20, centerX+50, centerY+35);
		 */
		
		centerX += speedX;
		
		// move background as robot move on
		bg1.setSpeedY( MOVE_SPEED_Y / 5);
		bg2.setSpeedY( MOVE_SPEED_Y / 5);

	}

	public void moveRight() {
		speedX = MOVE_SPEED_X;
	}

	public void moveLeft() {
		speedX = -MOVE_SPEED_X;
	}
	
	public void stop() {
		speedX = 0;
	}
/*
	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}
*/
	/*
	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

	}
*/

	public void shoot() {
		Projectile p = new Projectile(centerX, centerY + 25);
		projectiles.add(p);
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
/*
	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}
*/
	public ArrayList getProjectiles() {
		return projectiles;
	}

}
