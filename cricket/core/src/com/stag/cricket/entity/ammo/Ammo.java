package com.stag.cricket.entity.ammo;

import com.badlogic.gdx.graphics.Texture;
import com.stag.cricket.TextureManager;

public enum Ammo {
	PLASMA(TextureManager.AMMO_RED_PLASMA, 1d);
	
	private Texture texture;
	private double speedMultiplier;
	
	Ammo(Texture texture, double speedMultiplier) {
		this.texture = texture;
		this.speedMultiplier = speedMultiplier;
	}
	
	public Texture getTexture() {
		return this.texture;
	}
	
	public double getSpeedMultiplier() {
		return this.speedMultiplier;
	}
}
