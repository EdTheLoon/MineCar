package com.edtheloon.MineCar;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class MCMain extends JavaPlugin {

	// PERMISSION STRINGS
	public static final String PERMISSION_CONTROL = "MineCar.control";
	public static final String PERMISSION_CREATE = "MineCar.create";
	public static final String PERMISSION_REMOVE = "MineCar.remove";
	public static final String PERMISSION_REMOVE_ALL = "MineCar.remove.all";
	public static final String PERMISSION_REMOVE_OWN = "MineCar.remove.own";
	public static final String PERMISSION_RELOAD = "MineCar.reload";
	public static final String PERMISSION_DESTROY_OTTHERS = "MineCar.destroy.others";

	//Listeners
	private final MCVehicleListener MCVehicleListener = new MCVehicleListener(this);
	private final MCPlayerListener MCPlayerListener = new MCPlayerListener(this);
	//private final MCInputListener MCInputListener = new MCInputListener(this);

	// Class Variables
	public PluginManager pluginManager;
	public Logger log = Logger.getLogger("Minecraft");
	// For Multi-World support we save the cars with the following node: world_name.player_name
	public static HashMap<String, Object> mineCars = new HashMap<String, Object>();
	public static HashMap<String, List<String>> playersList = new HashMap<String, List<String>>();
	// Constant movement task IDs
	public HashMap<String, Integer> taskID = new HashMap<String, Integer>();
	// Configuration files
	public static Configuration config;
	public static Configuration cars;
	public static Configuration players;

	public void onEnable() {

		// Check and Create/Load the configuration and cars YAML files
		Config.checkConf();

		// Output to console that we're loading the list of MineCars from a file and then load the list
		log.info("[MineCar] Loading list of MineCars...");
		mineCars = Functions.loadCars();

		// Output to console that we're loading the list of players from a file and then load the list
		//log.info("[MineCar] Loading list of players...");
		//playersList = Functions.loadPlayers();

		// Get the server's plugin manager so we can register commands and events
		pluginManager = getServer().getPluginManager();

		// Register some events
		pluginManager.registerEvent(Type.VEHICLE_DESTROY, MCVehicleListener, Priority.Normal, this);
		pluginManager.registerEvent(Type.PLAYER_ANIMATION, MCPlayerListener, Priority.Normal, this);
		// So we can give back cars if removed and the player wasn't online
		pluginManager.registerEvent(Type.PLAYER_JOIN, MCPlayerListener, Priority.Normal, this);
		pluginManager.registerEvent(Type.PLAYER_TELEPORT, MCPlayerListener, Priority.Normal, this);
		pluginManager.registerEvent(Type.PLAYER_PORTAL, MCPlayerListener, Priority.Normal, this);

		// DEBUG LISTENER
		pluginManager.registerEvent(Type.VEHICLE_DAMAGE, MCVehicleListener, Priority.Normal, this);

		// Register INPUT LISTENING events
		pluginManager.registerEvent(Type.CUSTOM_EVENT, new MCInputListener(this), Priority.Normal, this);

		// Set Commands to be our commandExecutor
		getCommand("minecar").setExecutor(new MCCommandsManager(this));

		// Output to server console that the plugin is enabled
		log.info("[MineCar] Version " + this.getDescription().getVersion() + " enabled");

	}

	public void onDisable() {

		// Output to server console that we're saving the cars list and then save the list
		log.info("[MineCar] Saving list of cars...");
		Functions.saveCars(mineCars);

		// Output to server console that we're saving the players list and then save the list
		//log.info("[MineCar] Saving list of players...");
		//Functions.savePlayers(playersList);

		// Output to server console that the plugin is disabled
		log.info("[MineCar] Version " + this.getDescription().getVersion() + " disabled");

	}

}
