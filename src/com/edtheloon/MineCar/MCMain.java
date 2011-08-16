package com.edtheloon.MineCar;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MCMain extends JavaPlugin {
	
	// PERMISSION STRINGS
	public static final String PERMISSION_CONTROL = "MineCar.control";
	public static final String PERMISSION_CREATE = "MineCar.create";
	public static final String PERMISSION_REMOVE = "MineCar.remove";
	
	// Class Variables
	public PluginManager pluginManager;
	public final Logger log = Logger.getLogger("Minecraft");
	public HashMap<String,Integer> mineCars;
	public File carsFile = new File("plugins/MineCars/cars.txt");
	
	public void onEnable() {
		
		// Output to console that we're loading the list of MineCars from a file and then load the list
		log.info("[MineCar] Loading list of MineCars...");
		mineCars = Functions.loadCars();
		
		// Get the server's plugin manager so we can register commands and events
		pluginManager = getServer().getPluginManager(); 
		
		// Register some events
		pluginManager.registerEvent(Type.VEHICLE_DESTROY, new MCVehicleListener(this), Priority.Normal, this);
		pluginManager.registerEvent(Type.PLAYER_ANIMATION, new MCPlayerListener(this), Priority.Normal, this);
		
		// Register INPUT LISTENING events
		pluginManager.registerEvent(Type.CUSTOM_EVENT, new MCInputListener(this), Priority.Normal, this);
		
		// Set Commands to be our commandExecutor
		getCommand("minecar").setExecutor(new Commands(this));
		
		// Output to server console that the plugin is enabled
		log.info("[MineCar] Version " + this.getDescription().getVersion() + " enabled");
		
	}
	
	public void onDisable() {
		
		// Output to server console that we're saving the cars list and then load the list
		log.info("[MineCar] Saving list of cars...");
		Functions.saveCars(mineCars);
		
		// Output to server console that the plugin is disabled
		log.info("[MineCar] Version " + this.getDescription().getVersion() + " disabled");
		
	}

}
