package com.edtheloon.MineCar;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerListener;

public class MCPlayerListener extends PlayerListener {
	
	// Class Variables
	private MCMain plugin;
	
	// CONSTRUCTOR
	public MCPlayerListener(MCMain plug) {
		this.plugin = plug;
	}
	
	public void onPlayerAnimation(PlayerAnimationEvent event) {
		// Variables
		Player player = event.getPlayer();
		
		// Check to see if the player is inside a vehicle
		if (player.isInsideVehicle()) {
			// Check that the vehicle the player is in is their MineCar
			if (player.getVehicle().getEntityId() == plugin.mineCars.get(player.getName())) {
				// Now check that the player was swinging their arm
				if (event.getAnimationType() == PlayerAnimationType.ARM_SWING) {
					// TODO: DO SOME MINECAR TURNING CODE
				}
			}
		}
	}

}
