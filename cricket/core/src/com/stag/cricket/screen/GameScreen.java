package com.stag.cricket.screen;

import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.entity.EntityManager;

public class GameScreen extends Screen {

	public GameScreen(OrthoCamera camera, EntityManager entityManager) {
		super(camera, entityManager);
	}

	@Override
	public void create() {
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
