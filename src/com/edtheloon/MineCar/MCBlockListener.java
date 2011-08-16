package com.edtheloon.MineCar;

import org.bukkit.event.block.BlockListener;

public class MCBlockListener extends BlockListener {

	// Class Variables
	private MCMain plugin;
	
	// CONSTRUCTOR
	public MCBlockListener (MCMain plug) {
		this.plugin = plug;
	}
}
