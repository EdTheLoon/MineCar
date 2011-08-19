package com.edtheloon.MineCar;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class PermissionsManager {

	// Class variables
	private final MCMain plugin;
	public static PermissionHandler permHandler = null;
	public static boolean usePermsPlugin= false;
	// CONSTRUCTOR
	public PermissionsManager(MCMain plug) {
		this.plugin = plug;
	}

	public void loadPerm(){
		Plugin permPlug1 = plugin.getServer().getPluginManager().getPlugin("Permissions");
		if (permPlug1 != null){
			permHandler = ((Permissions) permPlug1).getHandler();
			usePermsPlugin = true;
		}
	}

	// Permission check for Player Objects
	public static boolean hasPerm(Player player, String permNode){
		if (Config.useBukkit){
			if (player.hasPermission(permNode)){
				return true;
			}
			else if (player.isOp()){
				return true;
			}
		}
		if (usePermsPlugin){
			if (permHandler.has(player, permNode)){
				return true;
			}
			else if (player.isOp()){
				return true;
			}
		}
		return false;
	}

	// Permission check for CommandSender Objects
	public static boolean hasPerm(CommandSender sender, String permNode){
		if (Config.useBukkit){
			if (sender.hasPermission(permNode)){
				return true;
			}
			else if (sender.isOp()){
				return true;
			}
		}
		if (usePermsPlugin){
			if (permHandler.has((Player) sender, permNode)){
				return true;
			}
			else if (sender.isOp()){
				return true;
			}
		}
		return false;
	}
}
