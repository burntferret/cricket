package com.stag.cricket.screen;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.stag.cricket.MainCricket;
import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.entity.EntityManager;
import com.stag.cricket.entity.monsters.BlueDude;
import com.stag.cricket.entity.monsters.Enemy;
import com.stag.cricket.utils.math.Geometry;

public class LevelOne extends Level {

	public static int NUMBER_OF_ENEMIES = 15;
	public static float MINIMUM_DISTANCE = 100f;
	
	public LevelOne(OrthoCamera camera, EntityManager entityManager) {
		super(camera, entityManager);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public Screen getNextScreen() {
		return null;
	}

	@Override
	public Array<Enemy> getEnemyInstantiations() {
		Array<Enemy> enemies = new Array<Enemy>();
		
		for(int i=0; i<NUMBER_OF_ENEMIES; i++) {
			
			Vector2 pos = new Vector2();
			BlueDude enemy = new BlueDude(pos, super.getEntityManaget());
			double distance = 0;
			double tempDistance = 0; 
			
			for(int j=0; distance < MINIMUM_DISTANCE && j < 50; j++) {
				float x = MathUtils.random((float) (MainCricket.WIDTH*MINIMUM_DISTANCE_MULTIPLIER), (float) (MainCricket.WIDTH*MAXIMUM_DISTANCE_MULTIPLIER));
				float y = MathUtils.random(0, (float) (MainCricket.HEIGHT-enemy.getTexture().getHeight()));
				
				pos.set(x, y);
				
				if(enemies.size == 0) {
					break;
				}
				
				for(Enemy e : enemies) {
					tempDistance = Geometry.getDistanceBetweenTwoPoints(e.getPosition(), pos);
					if(j==0 || tempDistance < distance) {
						distance = tempDistance;
					}
				}
			} 
			
			enemies.add(enemy);
		}
		
		return enemies;
	}

}
