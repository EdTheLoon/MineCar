package com.edtheloon.MineCar;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;

public class MCServerListener extends ServerListener {

	// Class variables
	@SuppressWarnings("unused")
	private MCMain plugin;
	
	// CONSTRUCTOR
	public MCServerListener (MCMain plug) {
		this.plugin = plug;
	}
	
	public void onPluginEnable (PluginEnableEvent event) {
		
	}
	
	public void onPluginDisable (PluginDisableEvent event) {
		
	}
}
