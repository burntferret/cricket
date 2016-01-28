package com.stag.cricket.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.stag.cricket.TextureManager;
import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.input.DirectionEnum;
import com.stag.cricket.input.InputManager;

public class Player extends Entity {

	public static final int STARTING_FIRE_INTERVAL = 250; // in milliseconds
	public static final int STARTING_LIVES = 3;
	
	public int currentScore; 
	public int currentLives;
	
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
