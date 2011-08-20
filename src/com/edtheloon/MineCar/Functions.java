package com.edtheloon.MineCar;

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

	public static HashMap<String, Object> loadCars() {

		// Declare and initialise variables
		HashMap<String, Object> cars = new HashMap<String, Object>();
		cars = (HashMap<String, Object>) plugin.carsFile.getAll();
		return cars;
	}

	public static void saveCars(HashMap<String, Object> cars) {
		for (Map.Entry<String, Object> cars_entry : cars.entrySet()){
				plugin.carsFile.setProperty(cars_entry.getKey(), cars_entry.getValue());
		}
	}

	public static boolean isDerailed(Minecart cart) {
		Location loc = cart.getLocation();
		loc.setY(loc.getY() - 1);
		Block block = loc.getBlock();
		Material blockType = block.getType();
		if (blockType == Material.POWERED_RAIL || blockType == Material.RAILS || blockType == Material.DETECTOR_RAIL)
			return true;
		else
			return false;
	}

	public static boolean hasMinecart(Player player) {
		PlayerInventory pi = player.getInventory();
		if (pi.contains(Material.MINECART)) return true; else return false;
	}

}
