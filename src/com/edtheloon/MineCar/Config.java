package com.edtheloon.MineCar;

import java.io.File;
import java.util.List;

import org.bukkit.util.config.Configuration;

public class Config {

	private static MCMain plugin;

	public Config(MCMain mcMain){
		plugin = mcMain;
	}

	public static String dir = "plugins" + File.separator + "MineCar";
	public static File configFile = new File(dir + File.separator + "config.yml");
	public static File cars = new File(dir + File.separator + "cars.yml");
	public static Configuration config;
	public static int speed;
	public static boolean useBukkit;
	public static List<String> worlds;

	public static void checkConf() {
		// Create the config directory
		new File(dir).mkdir();
		// Do the files already exist?
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				setConfDefaults();
			}
			catch (Exception ex) {
				plugin.log.severe("[MineCar] Could not create config.yml!");
			}
		}
		else {
			loadConfig();
		}
		if (!cars.exists()){
			try {
				cars.createNewFile();
				plugin.carsFile = new Configuration(cars);
			}
			catch (Exception ex) {
				plugin.log.severe("[MineCar] Could not create cars.yml!");
			}
		}
		else {
			plugin.carsFile = new Configuration(cars);
		}
	}

	private static void setConfDefaults() {
		config.setProperty("Minecar.Speed", 1);
		config.setProperty("Permissions.useBukkit", false);
		config.setProperty("Worlds", null);
		config.save();
	}

	public static void loadConfig() {
		config.load();
		speed = getInt("Minecar.speed", 1);
		useBukkit = getBoolean("Permissions.useBukkit", false);
		worlds = getStringList("Worlds", null);
	}

	// Functions for AutoUpdating the Config.yml
	public static Object getProperty(String path, Object def) {
		if(isNull(path))
			return addProperty(path, def);
		return config.getProperty(path);
	}

	public static Integer getInt(String path, Integer def) {
		if(isNull(path))
			return (Integer) addProperty(path, def);
		return config.getInt(path, def);
	}

	public static Boolean getBoolean(String path, Boolean def) {
		if(isNull(path))
			return (Boolean) addProperty(path, def);
		return config.getBoolean(path, def);
	}

	public static List<String> getStringList(String path, List<String> def) {
		if (isNull(path))
			return null;
		return config.getStringList(path, def);
	}

	private static Object addProperty(String path, Object val) {
		config.setProperty(path, val);
		return val;
	}

	private static Boolean isNull(String path) {
		return config.getProperty(path) == null;
	}
}
