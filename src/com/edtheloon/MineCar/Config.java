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
	static List<String> def;

	public static void checkConf() {

		// Just to include a List by default
		def.add("ex_world1");
		def.add("ex_world2");
		def.add("ex_world3");


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
		config.setProperty("Worlds", def);
		config.save();
	}

	public static void loadConfig() {
		config.load();
		speed = getInt("Minecar.speed", 1);
		useBukkit = getBoolean("Permissions.useBukkit", false);
		worlds = config.getStringList("Worlds", def);

		//Just in case something happens, lets save the changes, if any
		config.save();
		config.load();
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

	@SuppressWarnings("unchecked")
	public static List<String> getStringList(String path, List<String> def) {
		if (isNull(path))
			return (List<String>) addProperty(path, def);
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
