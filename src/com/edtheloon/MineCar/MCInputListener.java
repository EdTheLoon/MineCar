package com.edtheloon.MineCar;

import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MCInputListener extends InputListener {
	
	// Class variables
	private MCMain plugin;
	
	// CONSTRUCTOR
	public MCInputListener(MCMain plug) {
		this.plugin = plug;
	}
	
	public void onKeyPressedEvent(KeyPressedEvent event) {
		
		// Declare and initialise variables
		SpoutPlayer player = event.getPlayer();
		Keyboard key = event.getKey();
		ScreenType screen = event.getScreenType();
	
		// First check that the key is pressed in a game screen
		if (screen == ScreenType.GAME_SCREEN) {
			// Check if we need to do anything with this key press
			if (key == Keyboard.KEY_W) { // W was pressed
				
				// TODO: DEBUG ONLY, REMOVE THIS LINE
				player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				
				// CarControl.moveForward(); - Just a demonstration of how I think we should do this
				
			}
		}
	}

}
