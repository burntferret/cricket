package com.stag.cricket.entity;

import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.MainCricket;
import com.stag.cricket.TextureManager;

public class Projectile extends Entity{

	public Projectile(Vector2 pos) {
		super(TextureManager.AMMO_RED_PLASMA, pos, new Vector2(5, 0));
	}

	@Override
	public void update() {
		super.position.add(super.direction);
	}

	
	public boolean isOffScreen() {
		return  super.position.y >= MainCricket.HEIGHT || 
			    (super.position.x - texture.getWidth()) <= 0 ||
			    super.position.x > MainCricket.WIDTH;
	}
}
