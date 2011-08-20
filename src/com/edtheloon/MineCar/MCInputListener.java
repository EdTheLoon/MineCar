package com.edtheloon.MineCar;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
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
				if (!plugin.mineCars.containsValue(cartID)) return;

				// If vehicle is on rails don't continue
				Minecart cart = (Minecart) player.getVehicle();
				if (!Functions.isDerailed(cart)) return;

				// If this isn't the player's MineCar then don't continue
				if (cartID != plugin.mineCars.get(player.getName()) && plugin.mineCars.containsValue(cartID)) {
					player.sendMessage(ChatColor.RED + "This isn't you're MineCar!");
					return;
				}

				// If player doesn't have permission to control then don't continue
				if (!PermissionsManager.hasPerm(player, MCMain.PERMISSION_CONTROL)) {
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

				// If vehicle is on rails don't continue
				Minecart cart = (Minecart) player.getVehicle();
				if (!Functions.isDerailed(cart)) return;

				// If player doesn't have permission to control then don't continue
				if (!PermissionsManager.hasPerm(player, MCMain.PERMISSION_CONTROL)) {
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

				// If vehicle is on rails don't continue
				Minecart cart = (Minecart) player.getVehicle();
				if (!Functions.isDerailed(cart)) return;

				// If player doesn't have permission to control then don't continue
				if (!PermissionsManager.hasPerm(player, MCMain.PERMISSION_CONTROL)) {
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

				// If vehicle is on rails don't continue
				Minecart cart = (Minecart) player.getVehicle();
				if (!Functions.isDerailed(cart)) return;

				// If player doesn't have permission to control then don't continue
				if (!PermissionsManager.hasPerm(player, MCMain.PERMISSION_CONTROL)) {
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

				// Is the world on the list for which MC is enabled? If not, abort.
				if (!Config.worlds.contains(player.getWorld().toString())){
					return;
				}

				// If player doesn't have permission to create then don't continue
				if (!PermissionsManager.hasPerm(player, MCMain.PERMISSION_CREATE)) {
					player.sendMessage(ChatColor.RED + "You don't have permission to create a MineCar!");
					return;
				}

				World world = player.getWorld();
				String playerName = player.getName();
				boolean hasCart = plugin.mineCars.containsKey(playerName);

				// If player doesn't already have a MineCar OR doesn't have a Minecart in inventory then don't continue
				if (!hasCart) {
					if (Functions.hasMinecart(player)) {
						PlayerInventory pi = player.getInventory();
						ItemStack item = new ItemStack(Material.MINECART);
						item.setAmount(1);
						pi.remove(item);
					} else {
						return;
					}
				}

				// Check that the player doesn't already have a MineCar. If so then delete it
				if (hasCart) {

					if (Functions.deleteMinecart(world, (Integer) plugin.mineCars.get(playerName))) {
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
				plugin.mineCars.put(world + "." +playerName, cartID);
				return;
			}

		}
	}

}
