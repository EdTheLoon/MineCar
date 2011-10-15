package com.edtheloon.MineCar;

import java.io.IOException;
import java.util.Map;

import com.edtheloon.MineCar.Commands.Reload;
import com.edtheloon.MineCar.Commands.Remove;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class MCCommandsManager implements CommandExecutor {

	// Class variables
	private MCMain plugin;

	// CONSTRUCTOR
	public MCCommandsManager (MCMain plug) {
		this.plugin = plug;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		//Any args given?
		if (args.length >= 1){
			// Reloads all Configuration files
			if (args[0].equalsIgnoreCase("reload")){
				Reload.reload(sender);
				return true;
			}

			// DEBUG COMMAND TO CHECK THE HASHMAP
			if(args[0].equalsIgnoreCase("check")){
				for(Map.Entry<String, Object> cars : MCMain.mineCars.entrySet()){
					((Player) sender).sendMessage("node: " + String.valueOf(cars.getKey()) + " car: " + String.valueOf(cars.getValue()));
				}
				return true;
			}
			// DEBUG COMMAND TO CHECK IF SAVECARS() WORKS
			if(args[0].equalsIgnoreCase("save")){
				((Player) sender).sendMessage("Saving cars to file...");
				try {
					Functions.saveCars(MCMain.mineCars);
				} catch (IllegalArgumentException e) {
					sender.sendMessage(ChatColor.RED + "[MineCar] Saving the list of cars failed: " + e.getMessage());
				} catch (IOException e) {
					sender.sendMessage(ChatColor.RED + "[MineCar] Saving the list of cars failed: " + e.getMessage());
				}
				return true;
			}
			// DEBUG COMMAND TO CHECK IF LOADCARS() WORKS
			if(args[0].equalsIgnoreCase("load")){
				((Player) sender).sendMessage("Loading cars from file...");
				MCMain.mineCars = Functions.loadCars();
				return true;
			}

			if (args[0].equalsIgnoreCase("remove")){
				// No second argument was given
				if (args.length == 1){
					if (sender instanceof ConsoleCommandSender){
						plugin.log.info("[MineCar] You need to specify a World and Player to remove a MineCar!");
						return true;
					}
					else {
						// Remove the MineCar of the commandsender in the world he is in
						Remove.remove(((Player) sender).getWorld(), sender);
						return true;
					}
				}
				else if (args.length >= 2 && args[1].equalsIgnoreCase("all")){
					// Removes ALL MineCars
					if (args.length == 2){
						Remove.removeAll(sender);
						return true;
					}
					// Removes all Minecars in the given World
					else {
						Remove.removeAll(sender, args[2]);
						return true;
					}
				}
				// Removes the given player's MineCar in the commandsender's world
				else if (args.length == 2){
					if (sender instanceof ConsoleCommandSender){
						plugin.log.info("[MineCar] You need to specify a World and Player to remove a MineCar!");
						return true;
					}
					else {
						Remove.remove(((Player) sender).getWorld(), args[1], sender);
						return true;
					}
				}
				else if (args.length == 3){
					// Removes the given player'S MineCar in the given world
					// args[1] = player, args[2] = world
					Remove.remove(args[1],args[2], sender);
					return true;
				}
			}
		}
		// Just in case the command is never handled correctly
		plugin.log.severe("[MineCar] Command: " + label + " was not handled!");
		return false;
	}

}
