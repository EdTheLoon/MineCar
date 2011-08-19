package com.edtheloon.MineCar;

import com.edtheloon.MineCar.Commands.Reload;
import com.edtheloon.MineCar.Commands.Remove;

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
			if (args[0].equalsIgnoreCase("reload")){
				Reload.reload(sender);
				return true;
			}
			if (args[0].equalsIgnoreCase("remove")){
				// No second argument was given
				if (args.length == 1){
					if (sender instanceof ConsoleCommandSender){
						plugin.log.info("[MineCar] You need to specify a World and Player to remove the MineCar!");
						return true;
					}
					else {
					Remove.remove(((Player) sender).getWorld(), sender);
					return true;
					}
				}
				else if (args.length >= 2 && args[2].equalsIgnoreCase("all")){
					if (args.length == 2){
						Remove.removeAll(sender);
						return true;
					}
					else {
						Remove.removeAll(sender, args[2]);
						return true;
					}
				}
				else if (args.length == 2){
					Remove.remove(((Player) sender).getWorld(),args[1], sender);
					return true;
				}
				else if (args.length == 3){
					Remove.remove(args[2], args[1], sender);
					return true;
				}
			}
		}
		// Just in case the command is never handled correctly
		plugin.log.severe("[MineCar] Command: " + label + " was not handled!");
		return false;
	}

}
