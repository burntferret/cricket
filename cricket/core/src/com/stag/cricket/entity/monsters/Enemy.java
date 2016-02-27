package com.stag.cricket.entity.monsters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.MainCricket;
import com.stag.cricket.entity.Entity;
import com.stag.cricket.entity.EntityManager;
import com.stag.cricket.entity.Player;
import com.stag.cricket.entity.ammo.Ammo;
import com.stag.cricket.entity.ammo.Projectile;
import com.stag.cricket.utils.math.Geometry;

public abstract class Enemy extends Entity {
	
	private EntityManager entityManager;
	private Ammo ammo;
	private long lastFired;
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
	
	public long getLastFired() {
		return this.lastFired;
	}
	
	public double getLuck() {
		return this.luck;
	}
	
	public void fire() {
		boolean isGreaterThanLuck = Math.random() >= this.luck;
		boolean isReadyForFire = System.currentTimeMillis()-this.lastFired >= this.maximumFireFrequency;
		boolean isOnScreen = this.position.x < MainCricket.WIDTH;
		boolean isFrontOfPlayer = this.entityManager.getPlayerPosition().cpy().x < this.position.x;
		
		if(isOnScreen && isFrontOfPlayer && isGreaterThanLuck && isReadyForFire) {
			this.lastFired = System.currentTimeMillis();
			Texture tex = this.entityManager.getPlayer().getTexture();
			Vector2 playerPosition = this.entityManager.getPlayerPosition().cpy().add(tex.getWidth()*3/4, tex.getHeight()/2);
			
			Vector2 projectilePosition = new Vector2(super.getPosition().cpy());
			Vector2 targetPosition = new Vector2(playerPosition);
			
			Projectile projectile = new Projectile(this.ammo, projectilePosition, targetPosition, 1f, true);
			
			this.entityManager.addEntity(projectile);
		}
	}
	
	public abstract float getMinMovementSpeed();
	public abstract float getMaxMovementSpeed();
}
