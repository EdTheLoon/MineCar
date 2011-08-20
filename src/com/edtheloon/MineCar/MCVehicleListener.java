package com.edtheloon.MineCar;


import org.bukkit.World;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
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
		// First check if the vehicle is a MineCar
		Vehicle vehicle = event.getVehicle();
		Player player = (Player) event.getAttacker();
		int id = event.getVehicle().getEntityId();
		World world = event.getAttacker().getWorld();
		if (vehicle.getClass() == Minecart.class) {
			Functions.deleteMinecart(world, id);
			plugin.mineCars.remove(player.getWorld().toString() + "." + player.getName());
		}
	}

}
