package com.edtheloon.MineCar;

import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MCInputListener extends InputListener {
	
	// Class variables
	@SuppressWarnings("unused")
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
			
			// KEY W
			if (key == Keyboard.KEY_W) { // W was pressed				
				// TODO: DEBUG ONLY, REMOVE THIS LINE
				player.sendMessage("[MineCar] Key Pressed: " + key.toString());				
				CarControl.moveForward(player);				
			}
			
			// KEY S
			if (key == Keyboard.KEY_S) {				
				// TODO: DEBUG ONLY, REMOVE THIS LINE
				player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				CarControl.moveBackward(player);
			}
			
			// KEY A
			if (key == Keyboard.KEY_A) {				
				// TODO: DEBUG ONLY, REMOVE THIS LINE
				player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				CarControl.turn(player, "left");
			}
			
			// KEY D
			if (key == Keyboard.KEY_D) {				
				// TODO: DEBUG ONLY, REMOVE THIS LINE
				player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				CarControl.turn(player, "right");
			}
			
		}
	}

}
