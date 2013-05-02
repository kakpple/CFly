package com.kilobolt.robotgame;


public class Background {
	
	private int bgX;
	private int bgY;
	private int speedY;
	
	public Background(int x, int y){
		bgX = x;
		bgY = y;
		speedY = 0;
	}
	
	public void update() {
		bgY += speedY;

		if (bgY >= 2160){
			bgY -= 4320;
		}
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getSpeedX() {
		return speedY;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
}
