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
	private MCMain plugin;

	// CONSTRUCTOR
	public MCVehicleListener (MCMain plug) {
		this.plugin = plug;
	}

	public void onVehicleDestroy (VehicleDestroyEvent event) {
		Vehicle vehicle = event.getVehicle();
		Player player = (Player) event.getAttacker();
		int id = event.getVehicle().getEntityId();
		World world = event.getAttacker().getWorld();

		// First check if the vehicle is a MineCar
		if (vehicle instanceof Minecart) {
			// Now check if it is the users MineCar, if not, does the user have permission to destroy other MineCars?
			if (vehicle.getUniqueId() == plugin.mineCars.get(world.getName() + "." + player.getName())){
				Functions.deleteMinecart(world, id);
				plugin.mineCars.remove(world.getName() + "." + player.getName());
			}
			else if (PermissionsManager.hasPerm(player, MCMain.PERMISSION_DESTROY_OTTHERS)){
				Functions.deleteMinecart(world, id);
				plugin.mineCars.remove(world.getName() + "." + player.getName());
			}
			else{
				player.sendMessage(ChatColor.RED + "You are not allowed to destroy other players MineCars!");
			}
		}
	}

	// DEBUG EVENT: What is the UUID of the object clicked...
	public void onVehicleDamage (VehicleDamageEvent event){
		Player player = (Player) event.getAttacker();
		Material item = player.getItemInHand().getType();

		if (item == Material.STICK){
			UUID id = event.getVehicle().getUniqueId();
			player.sendMessage("Minecart Entity ID: " + String.valueOf(id));
		}
	}
}
