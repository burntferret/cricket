package com.stag.cricket.entity;

import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.MainCricket;
import com.stag.cricket.TextureManager;

public class FireButton extends Entity {
	
	public static final float X_POSITION = MainCricket.WIDTH - DirectionalPad.X_PADDING - 
			TextureManager.FIRE_BUTTON.getWidth() - TextureManager.DIRECTIONAL_PAD.getWidth()/2 + TextureManager.FIRE_BUTTON.getWidth()/2;
	
	public static final float Y_POSITION = DirectionalPad.Y_PADDING + TextureManager.DIRECTIONAL_PAD.getHeight()/2 -
			TextureManager.FIRE_BUTTON.getHeight()/2;

	public FireButton() {
		super(TextureManager.FIRE_BUTTON, 
				new Vector2(X_POSITION, Y_POSITION), 
				new Vector2(0, 0));
	}

	@Override
	public void update() {
		
	}	
}
