package com.stag.cricket.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.stag.cricket.MainCricket;
import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.entity.ammo.Projectile;

public class EntityManager {
	
	private final Array<Entity> entities = new Array<Entity>();
	private Player player;
	private DirectionalCluster directionalCluster;
	private FireButton fireButton;
	
	public EntityManager(OrthoCamera camera) {
		this.directionalCluster = new DirectionalCluster();
		this.fireButton = new FireButton();
		this.player = new Player(new Vector2(15, MainCricket.HEIGHT/2 - Player.height()/2), new Vector2(0, 0), this, camera, this.directionalCluster, this.fireButton);
	}
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void update() {
		this.player.update();
		this.directionalCluster.update();
		this.fireButton.update();
		
		// update all entities
		for(Entity e : this.entities) {
			e.update();
		}
		
		// get and handle all projectiles
		Array<Projectile> allProjectiles = this.getProjectiles();
		Array<Projectile> friendlyFire = new Array<Projectile>();
		Array<Projectile> enemyFire = new Array<Projectile>();
		
		for(Projectile p : allProjectiles) {
			
			// remove projectiles that are off screen
			if(p.isOffScreen()) {
				this.entities.removeValue(p, false);
			} 
			
			// classify remaining into friendly and enemy
			else {
				if(p.isEnemyFire()) {
					enemyFire.add(p);
				} else {
					friendlyFire.add(p);
				}
			}
		}
		
		for(Projectile p : enemyFire) {
			if(this.player.getBounds().overlaps(p.getBounds())) {
				//TODO: need to do a game over or lose life method here
			}
		}
	}
	
	public void render(SpriteBatch spriteBatch) {
		for(Entity e : this.entities) {
			e.render(spriteBatch);
		}
		
		this.player.render(spriteBatch);
		this.directionalCluster.render(spriteBatch);
		this.fireButton.render(spriteBatch);
	}
	
	private Array<Projectile> getProjectiles() {
		Array<Projectile> projectiles = new Array<Projectile>();
		for(Entity e : this.entities) {
			if(e instanceof Projectile) {
				projectiles.add((Projectile) e);
			}
		}
		
		return projectiles;
	}
	
	public Vector2 getPlayerPosition() {
		return this.player.position.cpy();
	}
	
	public Player getPlayer() {
		return this.player;
	}
}
