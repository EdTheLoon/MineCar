package com.edtheloon.MineCar.Commands;

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

	public Remove(MCMain plug) {
		super(plug);
		Remove.plugin = plug;
	}

	// TODO: All remove commands need to return the Minecart after removing it.

	// Called if command '/minecar remove' is executed
	public static void remove(World world, CommandSender sender){
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE_OWN)){
			if(Functions.deleteMinecart(world, plugin.mineCars.get(((Player) sender).getName()))){
				plugin.mineCars.remove(((Player) sender).getName());
				sender.sendMessage(ChatColor.GREEN + "You successfully removed " + ((Player) sender).getName() + "'s MineCar.");
			}
			else {
				sender.sendMessage(ChatColor.RED + ((Player) sender).getName() + " does not own a MineCar.");
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to remove a MineCar.");
		}
	}

	// Called if command '/minecar remove <player>' is executed
	public static void remove(World world, String player, CommandSender sender){
		if (sender instanceof ConsoleCommandSender){
			if(Functions.deleteMinecart(world, plugin.mineCars.get(player))){
				plugin.mineCars.remove(player);
				plugin.log.info("[MineCar] You successfully removed " + player + "'s MineCar.");
			}
			else {
				plugin.log.info("[MineCar] "+ player + " does not own a MineCar.");
			}
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE)){
			if(Functions.deleteMinecart(world, plugin.mineCars.get(player))){
				plugin.mineCars.remove(player);
				sender.sendMessage(ChatColor.GREEN + "You successfully removed " + player + "'s MineCar.");
			}
			else {
				sender.sendMessage(ChatColor.RED + player + " does not own a MineCar.");
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to remove a MineCar.");
		}
	}

	// Called if command '/minecar remove <player> <world>' is executed
	public static void remove(String world, String player, CommandSender sender){
		if (sender instanceof ConsoleCommandSender){
			if(Functions.deleteMinecart(plugin.getServer().getWorld(world), plugin.mineCars.get(player))){
				plugin.mineCars.remove(player);
				plugin.log.info("[MineCar] You successfully removed " + player + "'s MineCar.");
			}
			else {
				plugin.log.info("[MineCar] "+ player + " does not own a MineCar.");
			}
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE)){
			if(Functions.deleteMinecart(plugin.getServer().getWorld(world), plugin.mineCars.get(player))){
				plugin.mineCars.remove(player);
				sender.sendMessage(ChatColor.GREEN + "You successfully removed " + player + "'s MineCar.");
			}
			else {
				sender.sendMessage(ChatColor.RED + player + " does not own a MineCar.");
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to remove a MineCar.");
		}
	}

	// Called if command '/minecar remove all' is executed
	public static void removeAll(CommandSender sender){
		if (sender instanceof ConsoleCommandSender){
			plugin.mineCars.clear();
			plugin.log.info("[MineCar] You successfully removed all MineCars");
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE_ALL)){
			plugin.mineCars.clear();
			sender.sendMessage(ChatColor.GREEN + "You successfully removed all MineCars");
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to remove a MineCar.");
		}
	}

	// Called if command '/minecar remove all <world>' is executed
	// TODO: Multi-world support of the HashMap etc.
	public static void removeAll(CommandSender sender, String world){
		if (sender instanceof ConsoleCommandSender){
			plugin.mineCars.clear();
			plugin.log.info("[MineCar] You successfully removed all MineCars");
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE_ALL)){
			plugin.mineCars.clear();
			sender.sendMessage(ChatColor.GREEN + "You successfully removed all MineCars");
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to remove a MineCar.");
		}
	}
}
