package com.edtheloon.MineCar;


import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleListener;

public class MCVehicleListener extends VehicleListener {

	// Class variables
	@SuppressWarnings("unused")
	private MCMain plugin;

	// CONSTRUCTOR
	public MCVehicleListener (MCMain plug) {
		this.plugin = plug;
	}

	public void onVehicleDestroy (VehicleDestroyEvent event) {
		Vehicle vehicle = event.getVehicle();
		Player player = (Player) event.getAttacker();
		UUID id = event.getVehicle().getUniqueId();
		World world = event.getAttacker().getWorld();

		// First check if the vehicle is a MineCar
		if (vehicle instanceof Minecart) {
			// Now check if it is the users MineCar, if not, does the user have permission to destroy other MineCars?
			if (vehicle.getUniqueId().equals(MCMain.mineCars.get(world.getName() + "." + player.getName()))){
				Functions.deleteMinecart(world, id);
				MCMain.mineCars.remove(world.getName() + "." + player.getName());
			}
			else if (MCMain.mineCars.containsValue(vehicle.getUniqueId()) && PermissionsManager.hasPerm(player, MCMain.PERMISSION_DESTROY_OTTHERS)){
				Functions.deleteMinecart(world, id);
				// Note: The cart will not be returned to the owner, but instead be popping as do normal carts upon beeing destroyed!
				MCMain.mineCars.remove(world.getName() + "." + player.getName());
			}
			else if (MCMain.mineCars.containsValue(vehicle.getUniqueId())){
				player.sendMessage(ChatColor.RED + "You are not allowed to destroy other players MineCars!");
				event.setCancelled(true);
			}
		}
	}

	// DEBUG EVENT: What is the UUID of the object clicked...
	public void onVehicleDamage (VehicleDamageEvent event){
		Player player = (Player) event.getAttacker();
		Material item = player.getItemInHand().getType();

		if (item == Material.STICK){
			UUID id = event.getVehicle().getUniqueId();
			player.sendMessage("Minecart UUID: " + String.valueOf(id));
		}
	}
}
