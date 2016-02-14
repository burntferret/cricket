package com.stag.cricket.entity.monsters;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.TextureManager;
import com.stag.cricket.entity.EntityManager;
import com.stag.cricket.entity.ammo.Ammo;

public class BlueDude extends Enemy{

	public static final int MAXIMUM_FIRE_FREQUENCY = 1000;
	public static final double LUCK = 0.5;
	public static final float MIN_MOVEMENT_SPEED = 1;
	public static final float MAX_MOVEMENT_SPEED = 5;
	
	public BlueDude(Vector2 pos, EntityManager entityManager) {
		super(TextureManager.BLUE_DUDE, pos, new Vector2(-MathUtils.random(MIN_MOVEMENT_SPEED, MAX_MOVEMENT_SPEED), 0), Ammo.PLASMA, entityManager, MAXIMUM_FIRE_FREQUENCY, LUCK);
	}

	@Override
	public void update() {
		super.fire();
		this.position.add(this.direction);
	}

	@Override
	public float getMinMovementSpeed() {
		return MIN_MOVEMENT_SPEED;
	}

	@Override
	public float getMaxMovementSpeed() {
		return MAX_MOVEMENT_SPEED;
	}

}
