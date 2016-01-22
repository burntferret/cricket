package com.stag.cricket.test;

import static org.junit.Assert.*;

import com.badlogic.gdx.math.Vector2;
import com.stag.cricket.utils.math.Geometry;

public class Test {

	@org.junit.Test
	public void test() {
		
		try {
			Vector2 pointA = new Vector2(0,0);
			Vector2 pointB = new Vector2(-1,-1);
			
			double angle = Geometry.getAngleInDegrees(pointA, pointB);
			System.out.println(angle);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
