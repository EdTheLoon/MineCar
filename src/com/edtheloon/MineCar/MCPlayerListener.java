package com.edtheloon.MineCar;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class MCPlayerListener extends PlayerListener {

	// Class Variables
	@SuppressWarnings("unused")
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
			if (player.getVehicle().getUniqueId() == (UUID) MCMain.mineCars.get(player.getWorld().getName() + "." + player.getName())) {
				// Now check that the player was swinging their arm
				if (event.getAnimationType() == PlayerAnimationType.ARM_SWING) {
					// TODO: SOME MINECAR TURNING CODE
					@SuppressWarnings("unused")
					float rotation = player.getLocation().getYaw();
				}
			}
		}
	}

	// Retrun the minecart if the player is on the list and he is in the right world.
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if (Functions.returnCars(player, MCMain.playersList)){
		player.sendMessage(ChatColor.GOLD + "Your destroyed MineCar has been put into your inventory.");
		}
	}

	public void onPlayerPortal(PlayerPortalEvent event){
		Player player = event.getPlayer();
		if (Functions.returnCars(player, MCMain.playersList)){
			player.sendMessage(ChatColor.GOLD + "Your destroyed MineCar has been put into your inventory.");
		}
	}

	public void onPlayerTeleport(PlayerTeleportEvent event){
		Player player = event.getPlayer();
		if (Functions.returnCars(player, MCMain.playersList)){
		player.sendMessage(ChatColor.GOLD + "Your destroyed MineCar has been put into your inventory.");
		}
	}
}
