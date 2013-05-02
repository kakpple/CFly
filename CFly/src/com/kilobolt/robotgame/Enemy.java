package com.kilobolt.robotgame;

import android.graphics.Rect;

public class Enemy {

	private int centerX;
	private int centerY;
	private int speedY;
	private int power;

	private Background bg = GameScreen.getBg1();
	private Robot robot = GameScreen.getRobot();

	public Rect r = new Rect(0, 0, 0, 0);
	public int health = 5;

	private int movementSpeed;

	// Behavioral Methods
	public void update() {
		follow();
		centerY += speedY;
		speedY = bg.getSpeedX() * 5 + movementSpeed;
		r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 35);

		if (Rect.intersects(r, Robot.yellowRed)) {
			checkCollision();
		}

	}

	private void checkCollision() {
		if (Rect.intersects(r, Robot.rect)|| Rect.intersects(r, Robot.rect2)
				|| Rect.intersects(r, Robot.rect3) || Rect.intersects(r, Robot.rect4)) {
		}
	}

	public void follow() {
		
		if (centerY < -95 || centerY > 810){
			movementSpeed = 0;
		}

		else if (Math.abs(robot.getCenterY() - centerY) < 5) {
			movementSpeed = 0;
		}

		else {

			if (robot.getCenterY() >= centerY) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}

	}

	public void die() {

	}

	public void attack() {

	}

	public int getPower() {
		return power;
	}

	public int getSpeedY() {
		return speedY;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedY) {
		this.speedY = speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

}