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
	
	public static Double getAngleInRadians(Vector2 referencePoint, Vector2 directionalPoint) {
		float x = directionalPoint.x - referencePoint.x;
		float y = directionalPoint.y - referencePoint.y;
		
		return Math.atan2(y,  x);
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
		double degrees = convertRadiansToDegrees(getAngleInRadians(referencePoint, directionalPoint));
		
		if((directionalPoint.y-referencePoint.y)<0) {
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
	
	
	/**
	 * Returns a Vector2 for a specified distance away from a starting point at a specified angle.
	 * @param angle
	 * @param startingPoint
	 * @param distance
	 * @return
	 */
	public static Vector2 getProjectedPoint(double angle, Vector2 startingPoint, double distance) {
		float x = (float) (Math.cos(convertDegreesToRadians(angle))*distance + startingPoint.x);
		float y = (float) (Math.sin(convertDegreesToRadians(angle))*distance + startingPoint.y);
		
		return new Vector2(x, y);
	}
	
	
	/**
	 * Calculates the directional vector between the starting point and target and returns 
	 * a scaled vector based on the input factor.
	 * @param startingPoint
	 * @param targetPoint
	 * @param factor
	 * @return
	 */
	public static Vector2 getScaledVector(Vector2 startingPoint, Vector2 targetPoint, double factor) {
		double angleInDegrees = getAngleInDegrees(startingPoint, targetPoint);
		Vector2 target = getProjectedPoint(angleInDegrees, startingPoint, factor);
		
		float x = target.x-startingPoint.x;
		float y = target.y-startingPoint.y;
		
		return new Vector2(x, y);
	}
	
	/**
	 * Returns the angle in degrees of the x/y coordinates in a vector.
	 * @param vector
	 * @return
	 */
	public static double getSimpleVectorAngleInDegrees(Vector2 vector) {
		return convertRadiansToDegrees(getSimpleVectorAngleInRadians(vector));
	}
	
	/**
	 * Returns the angle in radians of the x/y coordinates in a vector.
	 * @param vector
	 * @return
	 */
	public static double getSimpleVectorAngleInRadians(Vector2 vector) {
		return Math.atan2(vector.y, vector.x);
	}
	
}
