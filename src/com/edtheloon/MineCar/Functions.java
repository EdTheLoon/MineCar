package com.edtheloon.MineCar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class Functions {

	private static MCMain plugin;

	// CONSTRUCTOR
	public Functions (MCMain plug) {
		Functions.plugin = plug;
	}

	public static Integer spawnMinecart(World world, Location location) {
		Minecart cart = world.spawn(location, Minecart.class);

		return cart.getEntityId();
	}

	public static boolean deleteMinecart(World world, Integer id) {
		List<Entity> entities = world.getEntities();
		for (Entity e : entities) {
			if(e.getEntityId() == id) {
				e.remove();
				return true;
			}
		}
		return false;
	}

	public static HashMap<String,Integer> loadCars(File carsFile) {

		// Declare and initialise variables
		HashMap<String,Integer> cars = new HashMap<String, Integer>();
		String line = "";
		String[] loadCars;
		BufferedReader input;
		try {
			input = new BufferedReader(new FileReader(carsFile));
			while ((line = input.readLine()) != null){
				loadCars = line.split(":");
				cars.put(loadCars[0], Integer.parseInt(loadCars[1]));
			}
			input.close();
		}
		catch (FileNotFoundException e) {
			plugin.log.severe("[MineCar] Did not find CarsFile.txt!");
		}
		catch (IOException e) {
			plugin.log.severe("[MineCar] could not be opened!");
		}
		catch (NumberFormatException e){
			plugin.log.severe("[MineCar] CarsFile.txt is invalid!");
		}
		// Finally, return the loaded cars
		return cars;

	}

	public static void saveCars(HashMap<String,Integer> cars, File carsFile) {
		BufferedWriter output;

		try {
			output = new BufferedWriter(new FileWriter(carsFile));
			for (Map.Entry<String, Integer> entry : cars.entrySet()){
				output.write(entry.getKey() + ":" + entry.getValue().toString());
				output.newLine();
			}
			output.flush();
			output.close();
		}
		catch (IOException e) {
			plugin.log.severe("[MineCar] CarsFile.txt could not be opened!");
		}
	}

	public static boolean isDerailed(Minecart cart) {
		Location loc = cart.getLocation();
		loc.setY(loc.getY() - 1);
		Block block = loc.getBlock();
		Material blockType = block.getType();
		if (blockType == Material.POWERED_RAIL || blockType == Material.RAILS || blockType == Material.DETECTOR_RAIL) return true; else return false;
	}

	public static boolean hasMinecart(Player player) {
		PlayerInventory pi = player.getInventory();
		if (pi.contains(Material.MINECART)) return true; else return false;
	}

}
