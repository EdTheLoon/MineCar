package com.edtheloon.MineCar.Commands;

import org.bukkit.World;
import org.bukkit.command.CommandSender;

import com.edtheloon.MineCar.Functions;
import com.edtheloon.MineCar.MCCommandsManager;
import com.edtheloon.MineCar.MCMain;

public class Remove extends MCCommandsManager {

	private static MCMain plugin;

	public Remove(MCMain plug) {
		super(plug);
		Remove.plugin = plug;
	}

	public static void remove(CommandSender sender){

	}

	public static void remove(World world, String player, CommandSender sender){
		if(Functions.deleteMinecart(world, plugin.mineCars.get(player)))
			plugin.mineCars.remove(player);
	}

	public static void removeAll(){

	}
}
