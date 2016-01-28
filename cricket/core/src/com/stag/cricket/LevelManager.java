package com.stag.cricket;

public class LevelManager {
	
	private int currentLevelPointer = 1; 
	
	public int getCurrentLevelPointer() {
		return this.currentLevelPointer;
	}
	
	public void increaseCurrentLevelPointer() {
		this.currentLevelPointer++;
	}
	
	
}
