package com.stag.cricket.entity;

import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.TextureManager;

public class DirectionalButton extends Entity {

	public DirectionalButton(float x, float y) {
		super(TextureManager.DIRECTIONAL_BUTTON, new Vector2(x, y), new Vector2(0,0));
	}

	@Override
	public void update() {
		
	}

}
