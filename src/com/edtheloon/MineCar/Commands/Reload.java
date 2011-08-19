package com.edtheloon.MineCar.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.edtheloon.MineCar.Config;
import com.edtheloon.MineCar.MCCommandsManager;
import com.edtheloon.MineCar.MCMain;
import com.edtheloon.MineCar.PermissionsManager;

public class Reload extends MCCommandsManager {

	private static MCMain plugin;

	public Reload(MCMain plug) {
		super(plug);
		Reload.plugin = plug;
	}

	public static void reload(CommandSender sender){

		if (sender instanceof ConsoleCommandSender){
			Config.loadConfig();
			plugin.log.info("[MineCar] Configuration reloaded!");
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_RELOAD)){
			Config.loadConfig();
			sender.sendMessage(ChatColor.GREEN + "Configuration reloaded!");
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to reload the configuration.");
		}
	}

}
