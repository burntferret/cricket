package com.stag.cricket.entity;

import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.TextureManager;

public class DirectionalPad extends Entity {

	public static final float X_PADDING = 10;
	public static final float Y_PADDING = 10;
	
	public DirectionalPad() {
		super(TextureManager.DIRECTIONAL_PAD, new Vector2(X_PADDING, Y_PADDING), new Vector2(0,0));
	}

	@Override
	public void update() {
	}

}
