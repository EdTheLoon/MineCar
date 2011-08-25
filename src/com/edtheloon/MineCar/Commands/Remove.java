package com.edtheloon.MineCar.Commands;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.edtheloon.MineCar.Functions;
import com.edtheloon.MineCar.MCCommandsManager;
import com.edtheloon.MineCar.MCMain;
import com.edtheloon.MineCar.PermissionsManager;

public class Remove extends MCCommandsManager {

	private static MCMain plugin;
	// Otherwise we get a warning in console and command/Logger does not work.
	private static Logger log = Logger.getLogger("Minecraft");

	public Remove(MCMain plug) {
		super(plug);
		Remove.plugin = plug;
	}

	// Called if command '/minecar remove' is executed
	public static void remove(World world, CommandSender sender){
		String playerName = ((Player) sender).getName();
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE_OWN)){
			if(Functions.deleteMinecart(world, MCMain.mineCars.get(world.getName() + "." + playerName))){
				MCMain.mineCars.remove(world.getName() + "." + playerName);
				returnCars(playerName, world.getName(), MCMain.playersList);
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
			if(Functions.deleteMinecart(world, MCMain.mineCars.get(world.getName() + "." + player))){
				MCMain.mineCars.remove(world.getName() + "." + player);
				returnCars(player, world.getName(), MCMain.playersList);
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
			if(Functions.deleteMinecart(plugin.getServer().getWorld(world), MCMain.mineCars.get(world + "." + player))){
				MCMain.mineCars.remove(world + "." + player);
				returnCars(player, world, MCMain.playersList);
				log.info("[MineCar] You successfully removed " + player + "'s MineCar in: " + world );
				return;
			}
			else {
				plugin.log.info("[MineCar] "+ player + " does not own a MineCar.");
				return;
			}
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE)){
			if(Functions.deleteMinecart(plugin.getServer().getWorld(world), MCMain.mineCars.get(world + "." + player))){
				MCMain.mineCars.remove(world + "." + player);
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
			String[] keySplit;
			for (Map.Entry<String, Object> cars_entry : MCMain.mineCars.entrySet()){
				keySplit = cars_entry.getKey().split("\\.");
				// keySplit[1] = PlayerName, keySplit[0] = WorldName
				returnCars(keySplit[1], keySplit[0], MCMain.playersList);
			}
			MCMain.mineCars.clear();
			log.info("[MineCar] You successfully removed all MineCars");
			return;
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE_ALL)){
			String[] keySplit;
			for (Map.Entry<String, Object> cars_entry : MCMain.mineCars.entrySet()){
				sender.sendMessage(cars_entry.getKey());
				keySplit = cars_entry.getKey().split("\\.");
				// keySplit[1] = PlayerName, keySplit[0] = WorldName
				returnCars(keySplit[1], keySplit[0], MCMain.playersList);
			}
			MCMain.mineCars.clear();
			sender.sendMessage(ChatColor.GREEN + "You successfully removed all MineCars");
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
			return;
		}
	}

	// Called if command '/minecar remove all <world>' is executed
	public static void removeAll(CommandSender sender, String world){
		if (sender instanceof ConsoleCommandSender){
			String[] keySplit = {"",""};
			Iterator<Map.Entry<String, Object>> it = MCMain.mineCars.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Object> entry = it.next();
				keySplit = entry.getKey().split("\\.");
				// If the key contains the given world remove the entry
				if (keySplit[0].equalsIgnoreCase(world)){
					it.remove();
					returnCars(keySplit[1], world, MCMain.playersList);

				}
			}
			log.info("[MineCar] Successfully removed all cars in " + world);
			return;
		}
		if (PermissionsManager.hasPerm(sender, MCMain.PERMISSION_REMOVE_ALL)){
			String[] keySplit = {"",""};
			Iterator<Map.Entry<String, Object>> it = MCMain.mineCars.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Object> entry = it.next();
				keySplit = entry.getKey().split("\\.");
				// If the key contains the given world remove the entry
				if (keySplit[0].equalsIgnoreCase(world)){
					it.remove();
					returnCars(keySplit[1], world, MCMain.playersList);
				}
			}
			sender.sendMessage(ChatColor.GREEN + "Successfully removed all cars in " + ChatColor.DARK_PURPLE + world);
			return;
		}
		else {
			sender.sendMessage(ChatColor.RED + "You don't have permission to execute this command.");
			return;
		}
	}

	// Return minecarts to their owner if destroyed, or save them to a List if player is not online
	public static void returnCars (String player, String world, HashMap<String, List<String>> playersList){
		Player playerObj = Bukkit.getServer().getPlayer(player);
		// If player == null this means he's offline
		if (player != null){
			PlayerInventory pi = playerObj.getInventory();
			ItemStack item = new ItemStack(Material.MINECART);
			item.setAmount(1);
			pi.addItem(item);
		}
		else {
			List<String> players = playersList.get(world);
			players.add(player);
			playersList.put(world, players);
		}
	}
}
