package com.stag.cricket.entity;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.stag.cricket.TextureManager;
import com.stag.cricket.input.DirectionEnum;
import com.stag.cricket.input.InputManager;
import com.stag.cricket.utils.math.Geometry;

public class DirectionalCluster extends EntityCluster {

	public static final int DIRECTIONAL_THRESHOLD = 15;
	
	private DirectionalPad pad;
	private DirectionalButton button;
	
	public DirectionalCluster() {
		super(new Array<Entity>());
		
		Array<Entity> entities = new Array<Entity>();
		this.pad = new DirectionalPad();
		this.button = new DirectionalButton(this.pad.position.cpy().x + this.pad.texture.getWidth()/2 - TextureManager.DIRECTIONAL_BUTTON.getWidth()/2,
			this.pad.position.cpy().y + this.pad.texture.getWidth()/2 - TextureManager.DIRECTIONAL_BUTTON.getHeight()/2);
		entities.add(this.pad);
		entities.add(this.button);
		super.setEntities(entities);
	}

	@Override
	public void update() {
		
	}

	public DirectionalPad getDirectionalPad() { 
		return this.pad;
	}
	
	public DirectionalButton getDirectionalButton() {
		return this.button;
	}
	
	/**
	 * Retrieves the first touch within the circle containing the directional pad.<br>
	 * If there are none, returns null.
	 * @param touches
	 * @return
	 */
	public Vector2 getDirectionalPadTouch(Array<Vector2> touches) {
		if(touches != null && touches.size > 0) {
		
			Circle directionalPadBounds = new Circle(this.pad.getCenter(), new Vector2(this.pad.getCenter().x + this.pad.getTexture().getWidth()/2, this.pad.getCenter().y));
			
			for(Vector2 touch : touches) {
				if(directionalPadBounds.contains(touch)) {
					return touch;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Takes in touch within directional pad constraints and calculates which, if any, directions are valid and
	 * calls and returns {@link #getDirectionEnum}
	 * @param directionalPadTouch
	 * @return
	 */
	public DirectionEnum getTouchInputDirection(Vector2 directionalPadTouch) {
		double angle = Geometry.getAngleInDegrees(this.pad.getCenter(), directionalPadTouch);
		
		boolean isUpPressed =  angle>=45-DIRECTIONAL_THRESHOLD && angle<=135+DIRECTIONAL_THRESHOLD;
		boolean isRightPressed = angle<=45+DIRECTIONAL_THRESHOLD || angle>=315-DIRECTIONAL_THRESHOLD;
		boolean isDownPressed = angle>=225-DIRECTIONAL_THRESHOLD && angle<=315+DIRECTIONAL_THRESHOLD;
		boolean isLeftPressed = angle>=135-DIRECTIONAL_THRESHOLD && angle<=225+DIRECTIONAL_THRESHOLD;
		
		setDirectionalButtonPosition(angle, directionalPadTouch);
		
		return InputManager.getDirectionEnum(isUpPressed, isRightPressed, isDownPressed, isLeftPressed);
	}
	
	
	/**
	 * Based on position of touch within the directional pad constraints, sets the position of the button/joystick.
	 * @param angle
	 * @param directionalPadTouch
	 */
	public void setDirectionalButtonPosition(double angle, Vector2 directionalPadTouch) {
		double distance = Geometry.getDistanceBetweenTwoPoints(directionalPadTouch, this.pad.getCenter());
		float maxDistance = this.pad.getTexture().getWidth()/2 - this.button.getTexture().getWidth()/2;
		
		float y = directionalPadTouch.y; 
		float x = directionalPadTouch.x; 
		
		if(distance>maxDistance) {
			Vector2 projectedPoint = Geometry.getProjectedPoint(angle, this.pad.getCenter(), maxDistance);
			y = projectedPoint.y;
			x = projectedPoint.x;
		}
		
		Vector2 position = new Vector2(x-this.button.getTexture().getWidth()/2, y-this.button.getTexture().getHeight()/2);
		this.button.setPosition(position);
	}
	
	/**
	 * Sets default 0,0 position of directional button/joystick relative to the directional pad.
	 */
	public void setDirectionalButtonToCenter() {
		float x = this.pad.getCenter().x - this.button.getTexture().getWidth()/2;
		float y = this.pad.getCenter().y - this.button.getTexture().getHeight()/2;
		this.button.setPosition(new Vector2(x, y));
	}
	
}
