package com.stag.cricket.screen;

import com.badlogic.gdx.utils.Array;
import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.entity.EntityManager;
import com.stag.cricket.entity.monsters.Enemy;

public abstract class Level extends Screen {
	
	public static double MINIMUM_DISTANCE_MULTIPLIER = 1.5;
	public static double MAXIMUM_DISTANCE_MULTIPLIER = 4.5;

	public Level(OrthoCamera camera, EntityManager entityManager) {
		super(camera, entityManager);
		
		Array<Enemy> enemies = this.getEnemyInstantiations();
		
		try {
			for(Enemy enemy : enemies) {
				this.getEntityManaget().addEntity(enemy);
			}
		} catch (Exception e) {
			
		}
	}

	@Override
	public void update() {
		super.update();
	}
	
	public abstract Array<Enemy> getEnemyInstantiations();
}
