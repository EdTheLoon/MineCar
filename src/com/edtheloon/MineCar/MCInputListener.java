package com.edtheloon.MineCar;

import org.bukkit.ChatColor;
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
				// DEBUG ONLY, REMOVE THIS LINE
				//player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				
				Integer cartID = player.getVehicle().getEntityId();
				// If the vehicle isn't a MineCar don't continue
				if (!plugin.mineCars.containsKey(cartID)) return;
				
				// If this isn't the player's MineCar then don't continue
				if (cartID != plugin.mineCars.get(player.getName()) && plugin.mineCars.containsValue(cartID)) {
					player.sendMessage(ChatColor.RED + "This isn't you're MineCar!");
					return;
				}
				
				// If player doesn't have permission to control then don't continue
				if (!player.hasPermission(MCMain.PERMISSION_CONTROL)) {
					player.sendMessage(ChatColor.RED + "You don't have permission to drive!");
					return;
				}
				
				CarControl.moveForward(player);
				return;
			}
			
			// KEY S
			if (player.isInsideVehicle() && key == Keyboard.KEY_S) {				
				// DEBUG ONLY, REMOVE THIS LINE
				//player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				
				Integer cartID = player.getVehicle().getEntityId();
				// If the vehicle isn't a MineCar don't continue
				if (!plugin.mineCars.containsKey(cartID)) return;
				
				// If this isn't the player's MineCar then don't continue
				if (cartID != plugin.mineCars.get(player.getName()) && plugin.mineCars.containsValue(cartID)) {
					player.sendMessage(ChatColor.RED + "This isn't you're MineCar!");
					return;
				}
				
				// If player doesn't have permission to control then don't continue
				if (!player.hasPermission(MCMain.PERMISSION_CONTROL)) {
					player.sendMessage(ChatColor.RED + "You don't have permission to drive!");
					return;
				}
				
				CarControl.moveBackward(player);
				return;
			}
			
			// KEY A
			if (player.isInsideVehicle() && key == Keyboard.KEY_A) {				
				// DEBUG ONLY, REMOVE THIS LINE
				//player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				
				Integer cartID = player.getVehicle().getEntityId();
				// If the vehicle isn't a MineCar don't continue
				if (!plugin.mineCars.containsKey(cartID)) return;
				
				// If this isn't the player's MineCar then don't continue
				if (cartID != plugin.mineCars.get(player.getName()) && plugin.mineCars.containsValue(cartID)) {
					player.sendMessage(ChatColor.RED + "This isn't you're MineCar!");
					return;
				}
				
				// If player doesn't have permission to control then don't continue
				if (!player.hasPermission(MCMain.PERMISSION_CONTROL)) {
					player.sendMessage(ChatColor.RED + "You don't have permission to drive!");
					return;
				}
				
				CarControl.turn(player, "left");
				return;
			}
			
			// KEY D
			if (player.isInsideVehicle() && key == Keyboard.KEY_D) {				
				// DEBUG ONLY, REMOVE THIS LINE
				//player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				
				Integer cartID = player.getVehicle().getEntityId();
				// If the vehicle isn't a MineCar don't continue
				if (!plugin.mineCars.containsKey(cartID)) return;
				
				// If this isn't the player's MineCar then don't continue
				if (cartID != plugin.mineCars.get(player.getName()) && plugin.mineCars.containsValue(cartID)) {
					player.sendMessage(ChatColor.RED + "This isn't you're MineCar!");
					return;
				}
				
				// If player doesn't have permission to control then don't continue
				if (!player.hasPermission(MCMain.PERMISSION_CONTROL)) {
					player.sendMessage(ChatColor.RED + "You don't have permission to drive!");
					return;
				}
				
				CarControl.turn(player, "right");
				return;
			}
			
			// KEY M
			if (!player.isInsideVehicle() && key == Keyboard.KEY_M) {
				// DEBUG ONLY, REMOVE THIS LINE
				//player.sendMessage("[MineCar] Key Pressed: " + key.toString());
				
				// If player doesn't have permission to create then don't continue
				if (!player.hasPermission(MCMain.PERMISSION_CREATE)) {
					player.sendMessage(ChatColor.RED + "You don't have permission to create a MineCar!");
					return;
				}
				
				World world = player.getWorld();
				
				// Spawn MineCar code - WILL MOST LIKELY NEED CHANGING - THIS IS FOR TESTING
				String playerName = player.getName();
				
				// Check that the player doesn't already have a MineCar. If so then delete it
				if (plugin.mineCars.containsKey(playerName)) {
					if (Functions.deleteMinecart(world, plugin.mineCars.get(playerName))) {
						// REMOVE BELOW DEBUG LINE
						//player.sendMessage("[MineCar] You're previous MineCar has been deleted");
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
