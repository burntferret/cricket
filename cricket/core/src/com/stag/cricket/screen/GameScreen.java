package com.stag.cricket.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.entity.EntityManager;

public class GameScreen extends Screen {

	private OrthoCamera camera;
	
	private EntityManager entityManager;
	
	@Override
	public void create() {
		this.camera = new OrthoCamera();
		this.entityManager = new EntityManager(this.camera);
	}
	
	@Override
	public void update() {
		this.camera.update();
		this.entityManager.update();
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		spriteBatch.setProjectionMatrix(this.camera.combined);
		spriteBatch.begin();
		this.entityManager.render(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		this.camera.resize();
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public Screen getNextScreen() {
		return null;
	}

}
