package com.edtheloon.MineCar;

import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MCMain extends JavaPlugin {
	
	// Class Variables
	public static PluginManager pluginManager;
	public static final Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		
		// Get the server's plugin manager so we can register commands and events
		pluginManager = getServer().getPluginManager(); 
		
		// Register some events
		pluginManager.registerEvent(Type.PLUGIN_DISABLE, new MCServerListener(this), Priority.Monitor, this);
		pluginManager.registerEvent(Type.PLUGIN_ENABLE, new MCServerListener(this), Priority.Monitor, this);
		pluginManager.registerEvent(Type.VEHICLE_ENTER, new MCVehicleListener(this), Priority.Normal, this);
		pluginManager.registerEvent(Type.VEHICLE_EXIT, new MCVehicleListener(this), Priority.Normal, this);
		
		// Set Commands to be our commandExecutor
		getCommand("minecar").setExecutor(new Commands(this));
		
                log.info("MineCar version 0.1 enabled");
	}
	
	public void onDisable() {
		log.info("MineCar disabled");
	}

}