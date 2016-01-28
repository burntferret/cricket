package com.stag.cricket.screen;

public enum LevelEnum {
	
	LevelEnum1(new GameScreen(), 1);
	
	private final Screen screen;
	private final int level;
	
	LevelEnum(Screen screen, int level) {
		this.screen = screen;
		this.level = level;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public Screen getScreen() {
		return this.screen;
	}
}
