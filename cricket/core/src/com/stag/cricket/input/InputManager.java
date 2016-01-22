package com.stag.cricket.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.stag.cricket.camera.OrthoCamera;
import com.stag.cricket.entity.DirectionalCluster;

public class InputManager {
	
	/**
	 * If touches are sensed within the directional pad constraints, calculates and returns direction.<br>
	 * If no touches are found within the constraints, sees if there are any keyboard directional inputs.
	 * @param camera
	 * @param directionalCluster
	 * @return
	 */
	public static DirectionEnum getDirection(OrthoCamera camera, DirectionalCluster directionalCluster) {
		Vector2 directionalPadTouch = directionalCluster.getDirectionalPadTouch(camera);
		
		if(directionalPadTouch != null) {
			return directionalCluster.getTouchInputDirection(directionalPadTouch);
		} else {
			directionalCluster.setDirectionalButtonToCenter();
			return getKeyBoardInputDirection();
		}
	}
	
	/**
	 * Gets all touches on a phone (or clicks on desktop)
	 * @param camera
	 * @return
	 */
	public static Array<Vector2> getTouches(OrthoCamera camera) {
		Array<Vector2> touches = new Array<Vector2>();
		int i=0; 
		while(Gdx.input.isTouched(i)) {
			touches.add(camera.unprojectCoordinates(Gdx.input.getX(i), Gdx.input.getY(i)));
			i++;
		}
		
		return touches;
	}
	
	/**
	 * Figures out which, if any, directional keystrokes are pressed and calls and returns {@link #getDirectionEnum}
	 * @return
	 */
	private static DirectionEnum getKeyBoardInputDirection() {
		boolean isUpPressed = Gdx.input.isKeyPressed(Keys.W);
		boolean isRightPressed = Gdx.input.isKeyPressed(Keys.D);
		boolean isDownPressed = Gdx.input.isKeyPressed(Keys.S);
		boolean isLeftPressed = Gdx.input.isKeyPressed(Keys.A);
		
		return getDirectionEnum(isUpPressed, isRightPressed, isDownPressed, isLeftPressed);
	}
	
	/**
	 * Based on directional inputs, returns corresponding {@link DirectionEnum}.
	 * @param isUpPressed
	 * @param isRightPressed
	 * @param isDownPressed
	 * @param isLeftPressed
	 * @return
	 */
	public static DirectionEnum getDirectionEnum(boolean isUpPressed, boolean isRightPressed, boolean isDownPressed, boolean isLeftPressed) {
		if(isRightPressed) {
			if(isLeftPressed) {
				return DirectionEnum.NONE;
			} else if(isUpPressed) {
				return DirectionEnum.UP_RIGHT;
			} else if(isDownPressed) {
				return DirectionEnum.DOWN_RIGHT;
			} else {
				return DirectionEnum.RIGHT;
			}
		} else if(isLeftPressed) {
			if(isRightPressed) {
				return DirectionEnum.NONE;
			} else if(isUpPressed) {
				return DirectionEnum.UP_LEFT;
			} else if(isDownPressed) {
				return DirectionEnum.DOWN_LEFT;
			} else {
				return DirectionEnum.LEFT;
			}
		} else if(isUpPressed) {
			if(isDownPressed) {
				return DirectionEnum.NONE;
			}
			return DirectionEnum.UP;
		}  else if(isDownPressed) {
			return DirectionEnum.DOWN;
		} else {
			return DirectionEnum.NONE;
		}
	}
	
}
