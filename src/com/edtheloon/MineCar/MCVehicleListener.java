package com.edtheloon.MineCar;

import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleListener;
import org.bukkit.plugin.Plugin;

public class MCVehicleListener extends VehicleListener {

	// Class variables
	public static Plugin plugin;
	
	// CONSTRUCTOR
	public MCVehicleListener (Plugin plug) {
		this.plugin = plug;
	}
	
	public void onVehicleEnter (VehicleEnterEvent event) {
		
	}
	
	public void onVehicleExit (VehicleExitEvent event) {
		
	}
	
}
