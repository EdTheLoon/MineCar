package com.edtheloon.MineCar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

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

	public static void checkConf() throws IOException, IllegalArgumentException {

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
				MCMain.config = YamlConfiguration.loadConfiguration(configFile);
				setConfDefaults();
			}
			catch (IOException ex){
				plugin.log.info("[MineCar] Could not create config.yml!");
			}
		}
		else {
			MCMain.config = YamlConfiguration.loadConfiguration(configFile);
			loadConfig();
		}
		// Second cars.yml
		if (!carsFile.exists()){
			try {
				carsFile.createNewFile();
				MCMain.cars = YamlConfiguration.loadConfiguration(carsFile);
			}
			catch (IOException ex) {
				plugin.log.severe("[MineCar] Could not create cars.yml!");
			}
		}
		else {
			MCMain.cars = YamlConfiguration.loadConfiguration(carsFile);
		}
		// Third players.yml
		if (!playersFile.exists()){
			try {
				playersFile.createNewFile();
				MCMain.players = YamlConfiguration.loadConfiguration(playersFile);
			}
			catch (IOException ex) {
				plugin.log.severe("[MineCar] Could not create players.yml!");
			}
		}
		else {
			MCMain.players = YamlConfiguration.loadConfiguration(playersFile);
		}
	}

	// Set the default values for the config.yml
	private static void setConfDefaults() throws IOException, IllegalArgumentException {
		MCMain.config.set("Minecar.Speed", 1);
		MCMain.config.set("Permissions.useBukkit", false);
		MCMain.config.set("Worlds", def);
		MCMain.config.save(configFile);
	}

	// Load the config.yml and get stored values
	public static void loadConfig() throws IOException, IllegalArgumentException {
		MCMain.config = YamlConfiguration.loadConfiguration(configFile);
		speed = getInt("Minecar.Speed", 1);
		useBukkit = getBoolean("Permissions.useBukkit", false);
		worlds = getStringList("Worlds", def);

		//Just in case something happens, lets save the changes, if any
		MCMain.config.save(configFile);
	}

	// Functions for AutoUpdating the Config.yml
	public static Object getProperty(String path, Object def) {
		if(isNull(path))
			return addProperty(path, def);
		return MCMain.config.get(path);
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
		return (List<String>) MCMain.config.getList(path, def);
	}

	private static Object addProperty(String path, Object val) {
		MCMain.config.set(path, val);
		return val;
	}

	private static Boolean isNull(String path) {
		return MCMain.config.get(path) == null;
	}
}
