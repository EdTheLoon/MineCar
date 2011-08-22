package com.edtheloon.MineCar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.util.config.Configuration;

public class Config {

	private static MCMain plugin;

	public Config(MCMain mcMain){
		plugin = mcMain;
	}

	public static String dir = "plugins" + File.separator + "MineCar";
	public static File configFile = new File(dir + File.separator + "config.yml");
	public static File carsFile = new File(dir + File.separator + "cars.yml");
	public static File playersFile = new File(dir + File.separator + "players.yml");
	public static int speed;
	public static boolean useBukkit;
	public static List<String> worlds;
	static List<String> def = new ArrayList<String>();

	public static void checkConf() {

		// Just to include a List by default
		def.add("ex_world1");
		def.add("ex_world2");
		def.add("ex_world3");


		// Create the config directory
		new File(dir).mkdir();
		// Do the files already exist?
		// First config.yml
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				MCMain.config = new Configuration(configFile);
				setConfDefaults();
			}
			catch (IOException ex){
				plugin.log.info("[MineCar] Could not create config.yml!");
			}
		}
		else {
			MCMain.config = new Configuration(configFile);
			loadConfig();
		}
		// Second cars.yml
		if (!carsFile.exists()){
			try {
				carsFile.createNewFile();
				MCMain.cars = new Configuration(carsFile);
				MCMain.cars.load();
			}
			catch (IOException ex) {
				plugin.log.severe("[MineCar] Could not create cars.yml!");
			}
		}
		else {
			MCMain.cars = new Configuration(carsFile);
			MCMain.cars.load();
		}
		// Third players.yml
		if (!playersFile.exists()){
			try {
				playersFile.createNewFile();
				MCMain.players = new Configuration(playersFile);
				MCMain.players.load();
			}
			catch (IOException ex) {
				plugin.log.severe("[MineCar] Could not create players.yml!");
			}
		}
		else {
			MCMain.players = new Configuration(playersFile);
			MCMain.players.load();
		}
	}

	// Set the default values for the config.yml
	private static void setConfDefaults() {
		MCMain.config.setProperty("Minecar.Speed", 1);
		MCMain.config.setProperty("Permissions.useBukkit", false);
		MCMain.config.setProperty("Worlds", def);
		MCMain.config.save();
	}

	// Load the config.yml and get stored values
	public static void loadConfig() {
		MCMain.config.load();
		speed = getInt("Minecar.Speed", 1);
		useBukkit = getBoolean("Permissions.useBukkit", false);
		worlds = getStringList("Worlds", def);

		//Just in case something happens, lets save the changes, if any
		MCMain.config.save();
		MCMain.config.load();
	}

	// Functions for AutoUpdating the Config.yml
	public static Object getProperty(String path, Object def) {
		if(isNull(path))
			return addProperty(path, def);
		return MCMain.config.getProperty(path);
	}

	public static Integer getInt(String path, Integer def) {
		if(isNull(path))
			return (Integer) addProperty(path, def);
		return MCMain.config.getInt(path, def);
	}

	public static Boolean getBoolean(String path, Boolean def) {
		if(isNull(path))
			return (Boolean) addProperty(path, def);
		return MCMain.config.getBoolean(path, def);
	}

	@SuppressWarnings("unchecked")
	public static List<String> getStringList(String path, List<String> def) {
		if (isNull(path))
			return (List<String>) addProperty(path, def);
		return MCMain.config.getStringList(path, def);
	}

	private static Object addProperty(String path, Object val) {
		MCMain.config.setProperty(path, val);
		return val;
	}

	private static Boolean isNull(String path) {
		return MCMain.config.getProperty(path) == null;
	}
}
