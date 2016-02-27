package com.stag.cricket.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.stag.cricket.MainCricket;
import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.entity.ammo.Projectile;
import com.stag.cricket.entity.monsters.Enemy;

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
		
		Array<Enemy> enemies = new Array<Enemy>();
		Array<Projectile> friendlyFire = new Array<Projectile>();
		Array<Projectile> enemyFire = new Array<Projectile>();
		
		// update all entities and sort entities into nice groups
		for(Entity e : this.entities) {
			e.update();
			
			// check if enemy
			if(e instanceof Enemy) {
				enemies.add((Enemy) e);
			} 
			
			// check if projectile
			else if(e instanceof Projectile) {
				
				// check to see if it is off screen, if so delete it
				if(((Projectile) e).isOffScreen()) {
					this.entities.removeValue(e, false);
				} 
				
				// if not off screen, sort enemy and friendly fire
				else {
					if(((Projectile) e).isEnemyFire()) {
						enemyFire.add((Projectile) e);
					} else {
						friendlyFire.add((Projectile) e);
					}
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
	
	public Vector2 getPlayerPosition() {
		return this.player.position.cpy();
	}
	
	public Player getPlayer() {
		return this.player;
	}
}
