package com.edtheloon.MineCar.Commands;

import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.edtheloon.MineCar.Functions;
import com.edtheloon.MineCar.MCCommandsManager;
import com.edtheloon.MineCar.MCMain;
import com.edtheloon.MineCar.PermissionsManager;

public class Remove extends MCCommandsManager {

	private static MCMain plugin;
	// Otherwise we get a warning in console and command does not work.
	private static Logger log = Logger.getLogger("Minecraft");

	public Remove(MCMain plug) {
		super(plug);
		Remove.plugin = plug;
	}

	// TODO: All remove commands need to return the Minecart after removing it.

	// Called if command '/minecar remove' is executed
	public static void remove(World world, CommandSender sender){
		String playerName = ((Player) sender).getName();
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE_OWN)){
			if(Functions.deleteMinecart(world, (Integer) plugin.mineCars.get(world.getName() + "." + playerName))){
				plugin.mineCars.remove(world.getName() + "." + playerName);
				sender.sendMessage(ChatColor.GREEN + "You successfully removed " + playerName + "'s MineCar.");
				return;
			}
			else {
				sender.sendMessage(ChatColor.RED + playerName + " does not own a MineCar.");
				return;
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
			return;
		}
	}

	// Called if command '/minecar remove <player>' is executed
	public static void remove(World world, String player, CommandSender sender){
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE)){
			if(Functions.deleteMinecart(world, (Integer) plugin.mineCars.get(world.getName() + "." + player))){
				plugin.mineCars.remove(world.getName() + "." + player);
				sender.sendMessage(ChatColor.GREEN + "You successfully removed " + player + "'s MineCar.");
				return;
			}
			else {
				sender.sendMessage(ChatColor.RED + player + " does not own a MineCar in " + ChatColor.DARK_PURPLE + world.getName());
				return;
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
			return;
		}
	}

	// Called if command '/minecar remove <player> <world>' is executed
	public static void remove(String player, String world, CommandSender sender){
		if (sender instanceof ConsoleCommandSender){
			if(Functions.deleteMinecart(plugin.getServer().getWorld(world), (Integer) plugin.mineCars.get(world + "." + player))){
				plugin.mineCars.remove(world + "." + player);
				log.info("[MineCar] You successfully removed " + player + "'s MineCar in: " + world );
				return;
			}
			else {
				plugin.log.info("[MineCar] "+ player + " does not own a MineCar.");
				return;
			}
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE)){
			if(Functions.deleteMinecart(plugin.getServer().getWorld(world), (Integer) plugin.mineCars.get(world + "." + player))){
				plugin.mineCars.remove(world + "." + player);
				sender.sendMessage(ChatColor.GREEN + "You successfully removed " + player + "'s MineCar in: " + ChatColor.DARK_PURPLE + world);
				return;
			}
			else {
				sender.sendMessage(ChatColor.RED + player + " does not own a MineCar.");
				return;
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
			return;
		}
	}

	// Called if command '/minecar remove all' is executed
	public static void removeAll(CommandSender sender){
		if (sender instanceof ConsoleCommandSender){
			plugin.mineCars.clear();
			log.info("[MineCar] You successfully removed all MineCars");
			return;
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE_ALL)){
			plugin.mineCars.clear();
			sender.sendMessage(ChatColor.GREEN + "You successfully removed all MineCars");
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
			return;
		}
	}

	// Called if command '/minecar remove all <world>' is executed
	// TODO: Code to remove per world.
	public static void removeAll(CommandSender sender, String world){
		if (sender instanceof ConsoleCommandSender){
			for (Map.Entry<String, Object> cars_entry : plugin.mineCars.entrySet()){
				@SuppressWarnings("unused")
				String s = cars_entry.getKey();
			}
			log.info("[MineCar] Currently not working.");
			return;
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE_ALL)){
			//plugin.mineCars.remove(world);
			sender.sendMessage(ChatColor.RED + "Currently not working.");
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
			return;
		}
	}
}
