package com.edtheloon.MineCar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Commands implements CommandExecutor {
	
	// Class variables
	private Plugin plugin;

	// CONSTRUCTOR
	public Commands (Plugin plug) {
		this.plugin = plug;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		// Just in case the command is never handled correctly
		MCMain.log.severe("[MineCar] Command: " + label + " was not handled!");
		return false;
	}
	
}
