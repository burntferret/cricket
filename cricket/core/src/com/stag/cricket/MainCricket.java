package com.stag.cricket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.entity.EntityManager;
import com.stag.cricket.screen.GameScreen;
import com.stag.cricket.screen.ScreenManager;

public class MainCricket extends ApplicationAdapter {
	
	public static int WIDTH = 800, HEIGHT = 480;
	public static final int STARTING_LIVES = 3;
	private SpriteBatch batch;
	private OrthoCamera camera;
	private EntityManager entityManager;
	
	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.camera = new OrthoCamera();
		this.entityManager = new EntityManager(this.camera);
		ScreenManager.setScreen(new GameScreen(this.camera, this.entityManager));
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
			ScreenManager.getCurrentScreen().resize();
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
