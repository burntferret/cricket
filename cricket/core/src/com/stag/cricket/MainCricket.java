package com.stag.cricket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stag.cricket.screen.GameScreen;
import com.stag.cricket.screen.ScreenManager;

public class MainCricket extends ApplicationAdapter {
	
	public static int WIDTH = 800, HEIGHT = 480;
	
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ScreenManager.setScreen(new GameScreen());
	}
	

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(ScreenManager.getCurrentScreen() != null) {
			ScreenManager.getCurrentScreen().update();
			ScreenManager.getCurrentScreen().render(batch);
		}
	}
	
	@Override
	public void dispose() {
		if(ScreenManager.getCurrentScreen() != null) {
			ScreenManager.getCurrentScreen().dispose();
		}
	}
	
	@Override
	public void resize(int width, int height) {
		if(ScreenManager.getCurrentScreen() != null) {
			ScreenManager.getCurrentScreen().resize(width, height);
		}
	}
	
	@Override
	public void pause() {
		if(ScreenManager.getCurrentScreen() != null) {
			ScreenManager.getCurrentScreen().pause();
		}
	}
	
	@Override
	public void resume() {
		if(ScreenManager.getCurrentScreen() != null) {
			ScreenManager.getCurrentScreen().resume();
		}
	}
}