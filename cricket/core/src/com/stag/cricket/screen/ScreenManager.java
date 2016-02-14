package com.stag.cricket.screen;

public class ScreenManager {

	private static Screen currentScreen = null;
	
	public static void setScreen(Screen screen) {
		if(currentScreen != null) {
			currentScreen.dispose();
		}
		currentScreen = screen;
	}
	
	public static Screen getCurrentScreen() {
		return currentScreen;
	}
	
}
