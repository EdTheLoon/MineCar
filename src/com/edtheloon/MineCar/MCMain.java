package com.edtheloon.MineCar;

import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MCMain extends JavaPlugin {
	
	// Class Variables
	public PluginManager pluginManager;
	public final Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		
		// Get the server's plugin manager so we can register commands and events
		pluginManager = getServer().getPluginManager(); 
		
		// Register some events
		pluginManager.registerEvent(Type.PLUGIN_DISABLE, new MCServerListener(this), Priority.Monitor, this);
		pluginManager.registerEvent(Type.PLUGIN_ENABLE, new MCServerListener(this), Priority.Monitor, this);
		pluginManager.registerEvent(Type.VEHICLE_ENTER, new MCVehicleListener(this), Priority.Normal, this);
		pluginManager.registerEvent(Type.VEHICLE_EXIT, new MCVehicleListener(this), Priority.Normal, this);
		pluginManager.registerEvent(Type.ENTITY_DAMAGE, new MCEntityListener(this), Priority.Normal, this); // Maybe ENTITY_DEATH would be more sufficient?
		
		// Register INPUT LISTENING events
		pluginManager.registerEvent(Type.CUSTOM_EVENT, new MCInputListener(this), Priority.Normal, this);
		
		// Set Commands to be our commandExecutor
		getCommand("minecar").setExecutor(new Commands(this));
		
		// Output to server console that the plugin is enabled
		log.info("[MineCar] Version " + this.getDescription().getVersion() + " enabled");
	}
	
	public void onDisable() {
		// Output to server console that the plugin is disabled
		log.info("[MineCar] Version " + this.getDescription().getVersion() + " disabled");
	}

}
