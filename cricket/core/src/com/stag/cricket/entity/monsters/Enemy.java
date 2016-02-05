package com.stag.cricket.entity.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.entity.Entity;
import com.stag.cricket.entity.EntityManager;
import com.stag.cricket.entity.ammo.Ammo;
import com.stag.cricket.entity.ammo.Projectile;

public abstract class Enemy extends Entity {
	
	private EntityManager entityManager;
	private Ammo ammo;
	private int lastFired;
	private int maximumFireFrequency;
	private double luck;
	
	public Enemy(Texture tex, Vector2 pos, Vector2 dir, Ammo ammo, EntityManager entityManager, 
			int maximumFireFrequency, double luck) {
		super(tex, pos, dir);
		this.entityManager = entityManager;
		this.ammo = ammo;
		this.maximumFireFrequency = maximumFireFrequency;
		this.luck = luck;
	}

	public Ammo getAmmo() {
		return this.ammo;
	}
	
	public void setAmmo(Ammo ammo) {
		this.ammo = ammo;
	}
	
	public int getLastFired() {
		return this.lastFired;
	}
	
	public double getLuck() {
		return this.luck;
	}
	
	public void fire() {
		if((int) Math.random() >= this.luck && System.currentTimeMillis()-this.lastFired >= this.maximumFireFrequency) {
			float x = -this.ammo.getTexture().getWidth();
			float y = super.getTexture().getHeight()/2 - this.ammo.getTexture().getHeight()/2;
			
			Projectile projectile = new Projectile(this.ammo, new Vector2(this.getPosition().cpy().add(x, y)), 
					this.entityManager.getPlayerPosition(), this.entityManager.getPlayer().getSpeedMultiplier(), true);
			
			this.entityManager.addEntity(projectile);
		}
	}
}
