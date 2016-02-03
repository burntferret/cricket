package com.stag.cricket.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.stag.cricket.TextureManager;
import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.entity.ammo.Ammo;
import com.stag.cricket.entity.ammo.Projectile;
import com.stag.cricket.input.DirectionEnum;
import com.stag.cricket.input.InputManager;

public class Player extends Entity {

	public static final int STARTING_FIRE_INTERVAL = 250; // in milliseconds
	public static final int STARTING_LIVES = 3;
	
	public int currentScore; 
	public int currentLives;
	public double speedMultiplier;
	public Ammo ammo;
	
	private final EntityManager entityManager;
	private final OrthoCamera camera;
	private long lastFired = System.currentTimeMillis();
	private final DirectionalCluster directionalCluster;
	private final FireButton fireButton;
	private Array<Vector2> touches;
	
	public Player(Vector2 position, Vector2 direction, EntityManager entityManager, OrthoCamera camera, DirectionalCluster directionalCluster,
			FireButton fireButton) {
		super(TextureManager.BLUE_SHIP_STEADY_STRAIGHT, position, direction);
		this.entityManager = entityManager;
		this.camera = camera;
		this.directionalCluster = directionalCluster;
		this.fireButton = fireButton;
		this.touches = new Array<Vector2>();
		this.currentLives = STARTING_LIVES;
		this.currentScore = 0;
		this.ammo = Ammo.PLASMA;
		this.speedMultiplier = 1d;
	}

	@Override
	public void update() {
		this.touches = InputManager.getTouches(camera);
		super.position.add(direction);
		
		DirectionEnum direction = InputManager.getDirection(this.directionalCluster, this.touches);
		boolean isFireButtonPressed = InputManager.isFireButtonPressed(this.fireButton, this.touches);
		
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
		
		if(isFireButtonPressed) {
			if(System.currentTimeMillis() - this.lastFired >= STARTING_FIRE_INTERVAL) {
				Vector2 position =  super.getPosition().cpy().add(texture.getWidth(), (texture.getHeight()/2 - TextureManager.AMMO_RED_PLASMA.getHeight()/2));
				this.entityManager.addEntity(new Projectile(this.ammo, position, position.cpy().add(1f,0f), this.speedMultiplier));
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
	
	public Ammo getAmmo() {
		return this.ammo;
	}
	
	public void setAmmo(Ammo ammo) {
		this.ammo = ammo;
	}
	
	public double getSpeedMultiplier() {
		return this.speedMultiplier;
	}
	
	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}
}
