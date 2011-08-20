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
		String playerName = ((Player) sender).getName();
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE_OWN)){
			if(Functions.deleteMinecart(world, (Integer) plugin.mineCars.get(world.getName() + "." + playerName))){
				plugin.mineCars.remove(world.getName() + "." + playerName);
				sender.sendMessage(ChatColor.GREEN + "You successfully removed " + playerName + "'s MineCar.");
			}
			else {
				sender.sendMessage(ChatColor.RED + playerName + " does not own a MineCar.");
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
		}
	}

	// Called if command '/minecar remove <player>' is executed
	public static void remove(World world, String player, CommandSender sender){
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE)){
			if(Functions.deleteMinecart(world, (Integer) plugin.mineCars.get(world.getName() + "." + player))){
				plugin.mineCars.remove(world.getName() + "." + player);
				sender.sendMessage(ChatColor.GREEN + "You successfully removed " + player + "'s MineCar.");
			}
			else {
				sender.sendMessage(ChatColor.RED + player + " does not own a MineCar in " + ChatColor.DARK_PURPLE + world.getName());
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
		}
	}

	// Called if command '/minecar remove <player> <world>' is executed
	public static void remove(String world, String player, CommandSender sender){
		if (sender instanceof ConsoleCommandSender){
			if(Functions.deleteMinecart(plugin.getServer().getWorld(world), (Integer) plugin.mineCars.get(world + "." + player))){
				plugin.mineCars.remove(world + "." + player);
				plugin.log.info("[MineCar] You successfully removed " + player + "'s MineCar in: " + world );
			}
			else {
				plugin.log.info("[MineCar] "+ player + " does not own a MineCar.");
			}
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE)){
			if(Functions.deleteMinecart(plugin.getServer().getWorld(world), (Integer) plugin.mineCars.get(world + "." + player))){
				plugin.mineCars.remove(world + "." + player);
				sender.sendMessage(ChatColor.GREEN + "You successfully removed " + player + "'s MineCar in: " + ChatColor.DARK_PURPLE + world);
			}
			else {
				sender.sendMessage(ChatColor.RED + player + " does not own a MineCar.");
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
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
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
		}
	}

	// Called if command '/minecar remove all <world>' is executed
	// TODO: Code to remove per world.
	public static void removeAll(CommandSender sender, String world){
		if (sender instanceof ConsoleCommandSender){
			//plugin.mineCars.remove(world);
			plugin.log.info("[MineCar] Currently not working.");
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE_ALL)){
			//plugin.mineCars.remove(world);
			sender.sendMessage(ChatColor.RED + "Currently not working.");
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
		}
	}
}
