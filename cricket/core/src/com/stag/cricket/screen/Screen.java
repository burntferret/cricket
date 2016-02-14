package com.stag.cricket.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.entity.EntityManager;

public abstract class Screen {
	
	private OrthoCamera camera;
	private EntityManager entityManager;
	public boolean hasEnded = false;
	
	public Screen(OrthoCamera camera, EntityManager entityManager) {
		this.camera = camera;
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManaget() {
		return this.entityManager;
	}
	
	public void setHasEnded(boolean hasEnded) {
		this.hasEnded = hasEnded;
	}
	
	public void render(SpriteBatch spriteBatch) {
		spriteBatch.setProjectionMatrix(this.camera.combined);
		spriteBatch.begin();
		this.entityManager.render(spriteBatch);
		spriteBatch.end();
	}
	
	public void update() {
		this.entityManager.update();
		this.camera.update();
	};
	
	public void resize() {
		this.camera.resize();
	}

	/*
	 * Abstract methods
	 */
	public abstract void dispose();
	
	public abstract void pause();
	
	public abstract void resume();
	
	public abstract Screen getNextScreen();
}
