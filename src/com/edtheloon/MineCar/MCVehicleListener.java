package com.edtheloon.MineCar;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Vehicle;
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
		// First check if the vehicle is a MineCar
		Vehicle vehicle = event.getVehicle();
		if (vehicle.getClass() == Minecart.class) {
			
		}
	}
	
}
