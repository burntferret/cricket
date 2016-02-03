package com.stag.cricket.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.entity.ammo.Ammo;

public abstract class Enemy extends Entity {
	
	private EntityManager entityManager;
	private Ammo ammo;
	
	public Enemy(Texture tex, Vector2 pos, Vector2 dir, Ammo ammo, EntityManager entityManager) {
		super(tex, pos, dir);
		this.entityManager = entityManager;
		this.ammo = ammo;
	}

	public Ammo getAmmo() {
		return this.ammo;
	}
	
	public void setAmmo(Ammo ammo) {
		this.ammo = ammo;
	}
	
	public void fire() {
		
	}
	
}
