package com.stag.cricket.entity.monsters;

import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.TextureManager;
import com.stag.cricket.entity.EntityManager;
import com.stag.cricket.entity.ammo.Ammo;

public class BlueDude extends Enemy{

	public static final int MAXIMUM_FIRE_FREQUENCY = 1000;
	public static final double LUCK = 0.5;
	
	public BlueDude(Vector2 pos, Vector2 dir, EntityManager entityManager) {
		super(TextureManager.BLUE_DUDE, pos, dir, Ammo.PLASMA, entityManager, MAXIMUM_FIRE_FREQUENCY, LUCK);
	}

	@Override
	public void update() {
		super.fire();
	}

}
