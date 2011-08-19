package com.edtheloon.MineCar;

import java.io.File;

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
		config.save();
	}

	public static void loadConfig() {
		config.load();
		speed = config.getInt("Minecar.speed", 1);
		useBukkit = config.getBoolean("Permissions.useBukkit", false);
	}
}
