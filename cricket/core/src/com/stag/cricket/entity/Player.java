package com.stag.cricket.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.TextureManager;
import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.input.DirectionEnum;
import com.stag.cricket.input.InputManager;

public class Player extends Entity {
	
	private final EntityManager entityManager;
	private final OrthoCamera camera;
	private long lastFired = System.currentTimeMillis();
	private final DirectionalCluster directionalCluster;
	
	public Player(Vector2 position, Vector2 direction, EntityManager entityManager, OrthoCamera camera, DirectionalCluster directionalCluster) {
		super(TextureManager.BLUE_SHIP_STEADY_STRAIGHT, position, direction);
		this.entityManager = entityManager;
		this.camera = camera;
		this.directionalCluster = directionalCluster;
	}

	@Override
	public void update() {
		position.add(direction);
		
		DirectionEnum direction = InputManager.getDirection(this.camera, this.directionalCluster);
		
		// deceleration, no up/down
		if(direction == DirectionEnum.LEFT) {
			super.setDirection(-300, 0);
			texture = TextureManager.BLUE_SHIP_DECEL_STRAIGHT;
		} 
		
		// acceleration, no up/down
		else if(direction == DirectionEnum.RIGHT) {
			super.setDirection(300, 0);
			texture = TextureManager.BLUE_SHIP_ACCEL_STRAIGHT;
		} 
		
		// just going up
		else if(direction == DirectionEnum.UP) {
			super.setDirection(0, 300);
			texture = TextureManager.BLUE_SHIP_STEADY_UP;
		} 
		
		// just going down
		else if(direction == DirectionEnum.DOWN) {
			super.setDirection(0, -300);
			texture = TextureManager.BLUE_SHIP_STEADY_DOWN;
		}
		
		// acceleration, diagonal up/right
		else if(direction == DirectionEnum.UP_RIGHT) {
			super.setDirection(300, 300);
			texture = TextureManager.BLUE_SHIP_ACCEL_UP;
		}
		
		// acceleration, diagonal down/right
		else if(direction == DirectionEnum.DOWN_RIGHT) {
			super.setDirection(300, -300);
			texture = TextureManager.BLUE_SHIP_ACCEL_DOWN;
		}
		
		// deceleration, diagonal up/left
		else if(direction == DirectionEnum.UP_LEFT) {
			super.setDirection(-300, 300);
			texture = TextureManager.BLUE_SHIP_DECEL_UP;
		}
		
		// deceleration, diagonal down/left
		else if(direction == DirectionEnum.DOWN_LEFT) {
			super.setDirection(-300, -300);
			texture = TextureManager.BLUE_SHIP_DECEL_DOWN;
		}
		
		// stop moving
		else {
			super.setDirection(0, 0);
			texture = TextureManager.BLUE_SHIP_STEADY_STRAIGHT;
		}
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			if(System.currentTimeMillis() - this.lastFired >= 250) {
				this.entityManager.addEntity(new Projectile(super.getPosition().cpy().add(texture.getWidth(), (texture.getHeight()/2 - TextureManager.AMMO_RED_PLASMA.getHeight()/2))));
				this.lastFired = System.currentTimeMillis();
			}
		}
	}
	
	public static int height() {
		return TextureManager.BLUE_SHIP_STEADY_STRAIGHT.getHeight();
	}
	
	public static int width() {
		return TextureManager.BLUE_SHIP_STEADY_STRAIGHT.getWidth();
	}
}
