package com.stag.cricket.entity.ammo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.MainCricket;
import com.stag.cricket.entity.Entity;
import com.stag.cricket.utils.math.Geometry;

public class Projectile extends Entity{
	
	public static final float DEFAULT_SPEED = 5f;
	
	private double speedMultiplier;
	private Vector2 direction;

	/**
	 * @param tex Desired Texture
	 * @param projectilePos Starting position
	 * @param targetPos This should be target position
	 * @param playerSpeedMultiplier
	 */
	public Projectile(Ammo ammo, Vector2 projectilePos, Vector2 targetPos, double playerSpeedMultiplier) {
		super(ammo.getTexture(), projectilePos, targetPos);
		this.speedMultiplier = ammo.getSpeedMultiplier()*playerSpeedMultiplier;
		
		this.direction = Geometry.getScaledVector(projectilePos, targetPos, this.speedMultiplier*DEFAULT_SPEED);
	}

	@Override
	public void update() {
		super.getPosition().add(this.direction);
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.draw(new TextureRegion(super.getTexture()), 
				super.getPosition().cpy().x, 
				super.getPosition().cpy().y, 
				(super.getPosition().cpy().x+(super.getTexture().getWidth()/2)), 
				(super.getPosition().cpy().y+(super.getTexture().getHeight()/2)), 
				(float) super.getTexture().getWidth(), (float) super.getTexture().getHeight(), 
				1f, 1f, (float) -Geometry.getSimpleVectorAngleInDegrees(this.direction));
	}
	
	public boolean isOffScreen() {
		return  super.getPosition().y >= MainCricket.HEIGHT || 
			    (super.getPosition().x - super.getTexture().getWidth()) <= 0 ||
			    super.getPosition().x > MainCricket.WIDTH;
	}
	
	public double getSpeedMultiplier() {
		return this.speedMultiplier;
	}
	
	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}
	
}
