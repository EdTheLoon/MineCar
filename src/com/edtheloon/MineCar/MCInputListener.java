package com.edtheloon.MineCar;

import org.bukkit.Location;
import org.bukkit.World;
import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MCInputListener extends InputListener {
	
	// Class variables
	private final MCMain plugin;
	
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
			if (player.isInsideVehicle() && key == Keyboard.KEY_W) {			
				// TODO: DEBUG ONLY, REMOVE THIS LINE
				player.sendMessage("[MineCar] Key Pressed: " + key.toString());				
				CarControl.moveForward(player);
				return;
			}
			
			// KEY S
			if (player.isInsideVehicle() && key == Keyboard.KEY_S) {				
				// TODO: DEBUG ONLY, REMOVE THIS LINE
				player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				CarControl.moveBackward(player);
				return;
			}
			
			// KEY A
			if (player.isInsideVehicle() && key == Keyboard.KEY_A) {				
				// TODO: DEBUG ONLY, REMOVE THIS LINE
				player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				CarControl.turn(player, "left");
				return;
			}
			
			// KEY D
			if (player.isInsideVehicle() && key == Keyboard.KEY_D) {				
				// TODO: DEBUG ONLY, REMOVE THIS LINE
				player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				CarControl.turn(player, "right");
				return;
			}
			
			// KEY M
			if (!player.isInsideVehicle() && key == Keyboard.KEY_M) {
				// TODO: DEBUG ONLY, REMOVE THIS LINE
				player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				World world = player.getWorld();
				
				// Spawn MineCar code - WILL MOST LIKELY NEED CHANGING - THIS IS FOR TESTING
				String playerName = player.getName();
				
				// Check that the player doesn't already have a MineCar. If so then delete it
				if (plugin.mineCars.containsKey(playerName)) {
					if (Functions.deleteMinecart(world, plugin.mineCars.get(playerName))) {
						// TODO: REMOVE BELOW DEBUG LINE
						player.sendMessage("[MineCar] You're previous MineCar has been deleted");
						plugin.mineCars.remove(playerName);
					} else {
						plugin.log.severe("[MineCar] Could not remove " + playerName + "'s minecart from world " + world.getName());
						plugin.mineCars.remove(playerName);
					}
				}

				// Get the block the player is looking at and then increment Y by 1
				Location loc = player.getTargetBlock(null, 5).getLocation();
				loc.setY(loc.getY() + 1);
				// Call the spawning code and then add the Minecarts ID to the mineCars HashMap
				Integer cartID = Functions.spawnMinecart(world, loc);
				plugin.mineCars.put(playerName, cartID);
				return;
			}
			
		}
	}

}
