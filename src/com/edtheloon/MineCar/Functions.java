package com.edtheloon.MineCar;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.entity.CraftMinecart;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Functions {
	
	public static Integer spawnMinecart(World world, Location location) {
		
		// TODO: ADD SOME SPAWNING CODE
		
		CraftMinecart cart = world.spawn(location, CraftMinecart.class);

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

}
