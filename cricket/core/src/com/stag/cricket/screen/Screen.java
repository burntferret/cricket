package com.stag.cricket.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Screen {
	
	public boolean hasEnded = false;
	public void setHasEnded(boolean hasEnded) {
		this.hasEnded = hasEnded;
	}

	public abstract void create();
	
	public abstract void update();
	
	public abstract void render(SpriteBatch spriteBatch);
	
	public abstract void resize(int width, int height);
	
	public abstract void dispose();
	
	public abstract void pause();
	
	public abstract void resume();
	
	public abstract Screen getNextScreen();
}
