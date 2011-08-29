package com.edtheloon.MineCar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Functions {

	@SuppressWarnings("unused")
	private static MCMain plugin;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger("Minecraft");

	// CONSTRUCTOR
	public Functions (MCMain plug) {
		plugin = plug;
	}

	// MineCar(t) functions
	public static UUID spawnMinecart(World world, Location location) {
		Minecart cart = world.spawn(location, Minecart.class);

		return cart.getUniqueId();
	}

	public static boolean deleteMinecart(World world, Object uuid) {
		List<Entity> entities = world.getEntities();
		for (Entity e : entities) {
			if(e.getUniqueId().equals(uuid)) {
				e.remove();
				return true;
			}
		}
		return false;
	}

	public static HashMap<String, Object> loadCars() {

		// Declare and initialise variables
		HashMap<String, Object> cars = new HashMap<String, Object>();
		Map<String, Object> raw = new TreeMap<String, Object>();
		raw = MCMain.cars.getAll();
		UUID id;

		// Convert the TreeMap into an HashMap as it's faster accessed
		if (raw.isEmpty()) {
			for (Map.Entry<String, Object> raw_entry : raw.entrySet()){
				id = UUID.fromString((String) raw_entry.getValue());
				cars.put(String.valueOf(raw_entry.getKey()), id );
			}
		}
		return cars;
	}

	public static void saveCars(HashMap<String, Object> cars) {
		for (Map.Entry<String, Object> cars_entry : cars.entrySet()){
			MCMain.cars.setProperty(String.valueOf(cars_entry.getKey()), cars_entry.getValue().toString());
		}
		MCMain.cars.save();
	}

	public static boolean isDerailed(Minecart cart) {
		Location loc = cart.getLocation();
		loc.setY(loc.getY() - 1);
		Block block = loc.getBlock();
		Material blockType = block.getType();
		if (blockType == Material.POWERED_RAIL || blockType == Material.RAILS || blockType == Material.DETECTOR_RAIL)
			return true;
		else
			return false;
	}

	public static boolean hasMinecart(Player player) {
		PlayerInventory pi = player.getInventory();
		if (pi.contains(Material.MINECART)) return true; else return false;
	}

	// Another returning function, called by the PlayerListener
	public static boolean returnCars(Player player, HashMap<String, List<String>> playersList){
		String world = player.getWorld().getName();
		String playerName = player.getName();
		if (playersList.containsKey(world)){
			List<String> players = playersList.get(world);
			if (players.contains(playerName)){
				PlayerInventory pi = player.getInventory();
				ItemStack item = new ItemStack(Material.MINECART);
				item.setAmount(1);
				pi.addItem(item);
				player.sendMessage(ChatColor.GREEN + "You're MineCar was returned to you");
				players.remove(playerName);
				playersList.put(world, players);
				return true;
			}
		}
		return false;
	}

	// Functions to save/load the list of players o whom the cart could not be returned
	public static HashMap<String, List<String>> loadPlayers() {

		// Declare and initialise variables
		HashMap<String, List<String>> players = new HashMap<String, List<String>>();
		if (Config.worlds != null) {
			for (String world: Config.worlds){
				players.put(world, MCMain.players.getStringList(world, null));

			}
		}
		return players;
	}

	public static void savePlayers(HashMap<String, List<String>> players) {
		for (Entry<String, List<String>> players_entry : players.entrySet()){
			MCMain.players.setProperty(players_entry.getKey(), players_entry.getValue());
		}
		MCMain.players.save();
	}
}
