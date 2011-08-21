package com.edtheloon.MineCar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class Functions {

	@SuppressWarnings("unused")
	private static MCMain plugin;
	private static Logger log = Logger.getLogger("Minecraft");

	// CONSTRUCTOR
	public Functions (MCMain plug) {
		plugin = plug;
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
		Map<String, Object> raw = new TreeMap<String, Object>();
		raw = Config.cars.getAll();

		// Convert the TreeMap into an HashMap as its faster accessed
		log.info("Loading...");
		log.info("Got entry: " + String.valueOf(raw.get("world.Lathanael")));
		for (Map.Entry<String, Object> raw_entry : raw.entrySet()){
			cars.put(String.valueOf(raw_entry.getKey()), raw_entry.getValue());
			log.info(String.valueOf(raw_entry.getKey()) + String.valueOf(raw_entry.getValue()) + " loaded");
		}
		return cars;
	}

	public static void saveCars(HashMap<String, Object> cars) {
		for (Map.Entry<String, Object> cars_entry : cars.entrySet()){
			Config.cars.setProperty(String.valueOf(cars_entry.getKey()), cars_entry.getValue());
		}
		Config.cars.save();
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
