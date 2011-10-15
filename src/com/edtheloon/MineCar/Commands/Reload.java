package com.edtheloon.MineCar.Commands;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.edtheloon.MineCar.Config;
import com.edtheloon.MineCar.MCCommandsManager;
import com.edtheloon.MineCar.MCMain;
import com.edtheloon.MineCar.PermissionsManager;

public class Reload extends MCCommandsManager {

	@SuppressWarnings("unused")
	private static MCMain plugin;
	// Otherwise we get a warning in console and command/Logger does not work.
	private static Logger log = Logger.getLogger("Minecraft");

	public Reload(MCMain plug) {
		super(plug);
		plugin = plug;
	}

	public static void reload(CommandSender sender){

		if (sender instanceof ConsoleCommandSender){
			try {
				Config.loadConfig();
			} catch (IllegalArgumentException e) {
				sender.sendMessage(ChatColor.RED + "[MineCar] Could not load configuration files!");
			} catch (IOException e) {
				sender.sendMessage(ChatColor.RED + "[MineCar] Could not load configuration files!");
			}
			log.info("[MineCar] Configuration reloaded!");
			return;
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_RELOAD)){
			try {
				Config.loadConfig();
			} catch (IllegalArgumentException e) {
				sender.sendMessage(ChatColor.RED + "[MineCar] Could not load configuration files!");
			} catch (IOException e) {
				sender.sendMessage(ChatColor.RED + "[MineCar] Could not load configuration files!");
			}
			sender.sendMessage(ChatColor.GREEN + "Configuration reloaded!");
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to reload the configuration.");
			return;
		}
	}

}
