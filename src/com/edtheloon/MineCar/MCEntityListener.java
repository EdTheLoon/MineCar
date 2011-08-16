package com.edtheloon.MineCar;

import org.bukkit.event.entity.EntityListener;

public class MCEntityListener extends EntityListener {
	
	// Class Variables
	@SuppressWarnings("unused")
	private MCMain plugin;
	
	// CONSTRUCTOR
	public MCEntityListener(MCMain plug) {
		this.plugin = plug;
	}

}
