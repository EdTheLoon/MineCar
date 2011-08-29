package com.edtheloon.MineCar;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.getspout.spoutapi.event.input.InputListener;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class MCInputListener extends InputListener {

	// Class variables
	private final MCMain plugin;
	private static SpoutPlayer player;

	// CONSTRUCTOR
	public MCInputListener(MCMain plug) {
		this.plugin = plug;
	}

	public void onKeyPressedEvent(KeyPressedEvent event) {

		// Declare and initialise variables
		SpoutPlayer player = event.getPlayer();
		// player for the task.
		MCInputListener.player = event.getPlayer();
		String worldName = event.getPlayer().getWorld().getName();
		String playerName = player.getName();
		Keyboard key = event.getKey();
		ScreenType screen = event.getScreenType();

		// First check that the key is pressed in a game screen
		if (screen == ScreenType.GAME_SCREEN) {

			// KEY W
			if (player.isInsideVehicle() && key == Keyboard.KEY_W) {
				// DEBUG ONLY, REMOVE THIS LINE
				//player.sendMessage("[MineCar] Key Pressed: " + key.toString());

				UUID cartID = player.getVehicle().getUniqueId();
				// If the vehicle isn't a MineCar don't continue
				if (!MCMain.mineCars.containsValue(cartID)) return;

				// If vehicle is on rails don't continue
				Minecart cart = (Minecart) player.getVehicle();
				if (Functions.isDerailed(cart)) return;

				// If this isn't the player's MineCar then don't continue
				if (!cartID.equals((UUID) MCMain.mineCars.get(worldName + "." + playerName))) {
					player.sendMessage(ChatColor.RED + "This isn't you're MineCar!");
					return;
				}

				// If player doesn't have permission to control then don't continue
				if (!PermissionsManager.hasPerm(player, MCMain.PERMISSION_CONTROL)) {
					player.sendMessage(ChatColor.RED + "You don't have permission to drive!");
					return;
				}

				// Get the scheduler for constant movement
				int id = Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin,
						new Runnable(){
							public void run(){
								CarControl.moveForward(MCInputListener.player);

							}
						}, 0, 3);
				plugin.taskID.put(worldName + "." + playerName, id);
				return;
			}

			// KEY S
			if (player.isInsideVehicle() && key == Keyboard.KEY_S) {
				// DEBUG ONLY, REMOVE THIS LINE
				//player.sendMessage("[MineCar] Key Pressed: " + key.toString());

				UUID cartID = player.getVehicle().getUniqueId();
				// If the vehicle isn't a MineCar don't continue
				if (!MCMain.mineCars.containsValue(cartID)) return;

				// If this isn't the player's MineCar then don't continue
				if (!cartID.equals(MCMain.mineCars.get(worldName + "." + playerName))) {
					player.sendMessage(ChatColor.RED + "This isn't you're MineCar!");
					return;
				}

				// If vehicle is on rails don't continue
				Minecart cart = (Minecart) player.getVehicle();
				if (Functions.isDerailed(cart)) return;

				// If player doesn't have permission to control then don't continue
				if (!PermissionsManager.hasPerm(player, MCMain.PERMISSION_CONTROL)) {
					player.sendMessage(ChatColor.RED + "You don't have permission to drive!");
					return;
				}

				// Get the scheduler for constant movement
				int id = Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin,
						new Runnable(){
							public void run(){
								CarControl.moveBackward(MCInputListener.player);

							}
						}, 0, 3);
				plugin.taskID.put(worldName + "." + playerName, id);
				return;
			}

			// KEY A
			if (player.isInsideVehicle() && key == Keyboard.KEY_A) {
				// DEBUG ONLY, REMOVE THIS LINE
				//player.sendMessage("[MineCar] Key Pressed: " + key.toString());

				UUID cartID = player.getVehicle().getUniqueId();
				// If the vehicle isn't a MineCar don't continue
				if (!MCMain.mineCars.containsValue(cartID)) return;

				// If this isn't the player's MineCar then don't continue
				if (!cartID.equals(MCMain.mineCars.get(worldName + "." + playerName))) {
					player.sendMessage(ChatColor.RED + "This isn't you're MineCar!");
					return;
				}

				// If vehicle is on rails don't continue
				Minecart cart = (Minecart) player.getVehicle();
				if (Functions.isDerailed(cart)) return;

				// If player doesn't have permission to control then don't continue
				if (!PermissionsManager.hasPerm(player, MCMain.PERMISSION_CONTROL)) {
					player.sendMessage(ChatColor.RED + "You don't have permission to drive!");
					return;
				}

				// Get the scheduler for constant movement
				int id = Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin,
						new Runnable(){
							public void run(){
								CarControl.turn(MCInputListener.player, "left");

							}
						}, 0, 3);
				plugin.taskID.put(worldName + "." + playerName, id);
				return;
			}

			// KEY D
			if (player.isInsideVehicle() && key == Keyboard.KEY_D) {
				// DEBUG ONLY, REMOVE THIS LINE
				//player.sendMessage("[MineCar] Key Pressed: " + key.toString());

				UUID cartID = player.getVehicle().getUniqueId();
				// If the vehicle isn't a MineCar don't continue
				if (!MCMain.mineCars.containsValue(cartID)) return;

				// If this isn't the player's MineCar then don't continue
				if (!cartID.equals(MCMain.mineCars.get(worldName + "." + playerName))) {
					player.sendMessage(ChatColor.RED + "This isn't you're MineCar!");
					return;
				}

				// If vehicle is on rails don't continue
				Minecart cart = (Minecart) player.getVehicle();
				if (Functions.isDerailed(cart)) return;

				// If player doesn't have permission to control then don't continue
				if (!PermissionsManager.hasPerm(player, MCMain.PERMISSION_CONTROL)) {
					player.sendMessage(ChatColor.RED + "You don't have permission to drive!");
					return;
				}

				// Get the scheduler for constant movement
				int id = Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin,
						new Runnable(){
							public void run(){
								CarControl.turn(MCInputListener.player, "right");

							}
						}, 0, 3);
				plugin.taskID.put(worldName + "." + playerName, id);
				return;
			}

			// KEY M
			if (!player.isInsideVehicle() && key == Keyboard.KEY_M) {
				// DEBUG ONLY, REMOVE THIS LINE
				player.sendMessage("[MineCar] Key Pressed: " + key.toString());

				// Is the world on the list for which MC is enabled? If not, abort.
				boolean worldsSet = false;
				try{
					worldsSet = Config.worlds.contains(worldName);
				}
				catch(NullPointerException npe){
					plugin.log.severe("[MineCar] NPE with world " + worldName);
				}
				if (!worldsSet){
					// DEBUG ONLY, REMOVE THIS LINE
					plugin.log.info("[MineCar] Someone tried to create a MineCar in an unset world");
					return;
				}

				// If player doesn't have permission to create then don't continue
				if (!PermissionsManager.hasPerm(player, MCMain.PERMISSION_CREATE)) {
					player.sendMessage(ChatColor.RED + "You don't have permission to create a MineCar!");
					return;
				}

				World world = player.getWorld();
				boolean hasCart = MCMain.mineCars.containsKey(worldName + "." + playerName);

				// If player doesn't already have a MineCar OR doesn't have a Minecart in inventory then don't continue
				if (!hasCart) {
					if (Functions.hasMinecart(player)) {
						PlayerInventory pi = player.getInventory();
						ItemStack item = new ItemStack(Material.MINECART);
						item.setAmount(1);
						int first = pi.first(item);
						pi.clear(first);
					} else {
						return;
					}
				}

				// Check that the player doesn't already have a MineCar. If so then delete it
				if (hasCart) {
					if (Functions.deleteMinecart(world, (UUID) MCMain.mineCars.get(worldName + "." + playerName))) {
						// REMOVE BELOW DEBUG LINE
						//player.sendMessage("[MineCar] You're previous MineCar has been deleted");
						MCMain.mineCars.remove(worldName + "." + playerName);
					} else {
						plugin.log.severe("[MineCar] Could not remove " + playerName + "'s minecart from world " + worldName);
						MCMain.mineCars.remove(worldName + "." + playerName);
					}
				}

				// Get the block the player is looking at and then increment Y by 1
				Location loc = player.getTargetBlock(null, 5).getLocation();
				loc.setY(loc.getY() + 1);
				// Call the spawning code and then add the Minecarts ID to the mineCars HashMap
				UUID cartID = Functions.spawnMinecart(world, loc);
				MCMain.mineCars.put(worldName + "." + playerName, cartID);
				//player.sendMessage("[MineCar] Put car: " + plugin.mineCars.get(worldName + "." + playerName) + " and node: " + worldName + "." + playerName + " into the Map." );
				return;
			}

		}
	}

	public void onKeyReleasedEvent(KeyReleasedEvent event){

		// Declare and initialise variables
		SpoutPlayer player = event.getPlayer();
		String worldName = event.getPlayer().getWorld().getName();
		String playerName = player.getName();
		Keyboard key = event.getKey();

		// KEY W
		if (player.isInsideVehicle() && key == Keyboard.KEY_W) {

			UUID cartID = player.getVehicle().getUniqueId();
			// If the vehicle isn't a MineCar don't continue
			if (!MCMain.mineCars.containsValue(cartID)) return;

			// Cancel the constant movement task and remove the taskID from the map
			if (plugin.taskID.get(worldName + "." + playerName) != null){
				Bukkit.getServer().getScheduler().cancelTask(plugin.taskID.get(worldName + "." + playerName));
				plugin.taskID.remove(worldName + "." + playerName);
			}
		}

		// KEY S
		if (player.isInsideVehicle() && key == Keyboard.KEY_S) {

			UUID cartID = player.getVehicle().getUniqueId();
			// If the vehicle isn't a MineCar don't continue
			if (!MCMain.mineCars.containsValue(cartID)) return;

			// Cancel the constant movement task and remove the taskID from the map
			if (plugin.taskID.get(worldName + "." + playerName) != null){
				Bukkit.getServer().getScheduler().cancelTask(plugin.taskID.get(worldName + "." + playerName));
				plugin.taskID.remove(worldName + "." + playerName);
			}
		}

		// KEY A
		if (player.isInsideVehicle() && key == Keyboard.KEY_A) {

			UUID cartID = player.getVehicle().getUniqueId();
			// If the vehicle isn't a MineCar don't continue
			if (!MCMain.mineCars.containsValue(cartID)) return;

			// Cancel the constant movement task and remove the taskID from the map
			if (plugin.taskID.get(worldName + "." + playerName) != null){
				Bukkit.getServer().getScheduler().cancelTask(plugin.taskID.get(worldName + "." + playerName));
				plugin.taskID.remove(worldName + "." + playerName);
			}
		}

		// KEY D
		if (player.isInsideVehicle() && key == Keyboard.KEY_D) {

			UUID cartID = player.getVehicle().getUniqueId();
			// If the vehicle isn't a MineCar don't continue
			if (!MCMain.mineCars.containsValue(cartID)) return;

			// Cancel the constant movement task and remove the taskID from the map
			if (plugin.taskID.get(worldName + "." + playerName) != null){
				Bukkit.getServer().getScheduler().cancelTask(plugin.taskID.get(worldName + "." + playerName));
				plugin.taskID.remove(worldName + "." + playerName);
			}
		}
	}

}
