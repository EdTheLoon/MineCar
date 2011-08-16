package com.edtheloon.MineCar;

import java.util.HashMap;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;

public class Functions {
	
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
	
	public static HashMap<String,Integer> loadCars() {
		
		// Declare and initialise variables
		HashMap<String,Integer> cars = new HashMap<String, Integer>();
		
		// TODO: ADD SOME LOADING CODE
		
		// Finally, return the loaded cars
		return cars; 
		
	}
	
	public static void saveCars(HashMap<String,Integer> cars) {
		
		// TODO: ADD SOME SAVING CODE
		
	}
	
	public static boolean isDerailed(Minecart cart) {
		Location loc = cart.getLocation();
		loc.setY(loc.getY() - 1);
		Block block = loc.getBlock();
		Material blockType = block.getType();
		if (blockType == Material.POWERED_RAIL || blockType == Material.RAILS || blockType == Material.DETECTOR_RAIL) return true; else return false;
	}

}
