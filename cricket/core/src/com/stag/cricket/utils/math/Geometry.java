package com.stag.cricket.utils.math;

import com.badlogic.gdx.math.Vector2;

public class Geometry {
	
	/**
	 * Returns the distance between two points,
	 * @param pointA
	 * @param pointB
	 * @return
	 */
	public static Double getDistanceBetweenTwoPoints(Vector2 pointA, Vector2 pointB) {
		float x = Math.abs(pointA.x - pointB.x);
		float y = Math.abs(pointA.y - pointB.y);
		
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	/**
	 * Calculates angle between two points relative to the first, reference point.
	 * <p>
	 * Always returns a positive value in degrees.
	 * @param referencePoint
	 * @param directionalPoint
	 * @return
	 */
	public static Double getAngleInDegrees(Vector2 referencePoint, Vector2 directionalPoint) {
		float x = directionalPoint.x - referencePoint.x;
		float y = directionalPoint.y - referencePoint.y;
		
		double degrees = convertRadiansToDegrees(Math.atan2(y, x));
		
		if(y<0) {
			degrees += 360;
		}
		
		return degrees;
	}
	
	/**
	 * Converts radians to degrees.
	 * @param radians
	 * @return
	 */
	public static Double convertRadiansToDegrees(double radians) {
		return radians*180/Math.PI;
	}
	
	/**
	 * Converts degrees to radians.
	 * @param degrees
	 * @return
	 */
	public static Double convertDegreesToRadians(double degrees) {
		return degrees*Math.PI/180;
	}
	
	
	public static Vector2 getProjectedPoint(double angle, Vector2 startingPoint, double distance) {
		float x = (float) (Math.cos(convertDegreesToRadians(angle))*distance + startingPoint.x);
		float y = (float) (Math.sin(convertDegreesToRadians(angle))*distance + startingPoint.y);
		
		return new Vector2(x, y);
	}
}
