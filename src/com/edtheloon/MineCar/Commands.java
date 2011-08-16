package com.edtheloon.MineCar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
	
	// Class variables
	private MCMain plugin;

	// CONSTRUCTOR
	public Commands (MCMain plug) {
		this.plugin = plug;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// Just in case the command is never handled correctly
		plugin.log.severe("[MineCar] Command: " + label + " was not handled!");
		return false;
	}
	
}
