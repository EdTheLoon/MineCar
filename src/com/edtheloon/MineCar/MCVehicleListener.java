package com.edtheloon.MineCar;

import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleListener;

public class MCVehicleListener extends VehicleListener {

	// Class variables
	@SuppressWarnings("unused")
	private MCMain plugin;
	
	// CONSTRUCTOR
	public MCVehicleListener (MCMain plug) {
		this.plugin = plug;
	}
	
	public void onVehicleEnter (VehicleEnterEvent event) {
		
	}
	
	public void onVehicleExit (VehicleExitEvent event) {
		
	}
	
}
