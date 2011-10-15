package com.edtheloon.MineCar;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class PermissionsManager {

	// Class variables
	@SuppressWarnings("unused")
	private final MCMain plugin;
	public static PermissionHandler permHandler = null;
	public static PermissionManager permExHandler = null;
	public static boolean usePerm = false;
	public static boolean usePermEx = false;
	// CONSTRUCTOR
	public PermissionsManager(MCMain plug) {
		this.plugin = plug;
	}

	public static void loadPerm(){
		Plugin permPlug1 = Bukkit.getServer().getPluginManager().getPlugin("Permissions");
		Plugin permPlug2 = Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx");
		if (permPlug1 != null){
			permHandler = ((Permissions) permPlug1).getHandler();
			usePerm = true;
		}
		if (permPlug2 != null){
			permExHandler = PermissionsEx.getPermissionManager();
			usePermEx = true;
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
		if (usePerm){
			if (permHandler.has(player, permNode)){
				return true;
			}
			else if (player.isOp()){
				return true;
			}
		}
		if (usePermEx){
			if (permExHandler.has(player, permNode)){
				return true;
			}
			else if (player.isOp()){
				return true;
			}
		}
		if (player.isOp()){
			return true;
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
		if (usePerm){
			if (permHandler.has((Player) sender, permNode)){
				return true;
			}
			else if (sender.isOp()){
				return true;
			}
		}
		if (usePermEx){
			if (permExHandler.has((Player) sender, permNode)){
				return true;
			}
			else if (sender.isOp()){
				return true;
			}
		}
		if (sender.isOp()){
			return true;
		}
		return false;
	}
}
