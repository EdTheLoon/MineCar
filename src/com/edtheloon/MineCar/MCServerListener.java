package com.edtheloon.MineCar;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.plugin.Plugin;

public class MCServerListener extends ServerListener {

	// Class variables
	Plugin plugin;
	
	// CONSTRUCTOR
	public MCServerListener (Plugin plug) {
		this.plugin = plug;
	}
	
	public void onPluginEnable (PluginEnableEvent event) {
		
	}
	
	public void onPluginDisable (PluginDisableEvent event) {
		
	}
}