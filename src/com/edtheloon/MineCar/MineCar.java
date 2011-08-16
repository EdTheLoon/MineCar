package com.edtheloon.MineCar;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

public abstract class MineCar implements Minecart {
	
	// Class variables	
	@SuppressWarnings("unused")
	private MCMain plugin; // This may not be needed. If removed just comment it out and alter the constructor
	public Player owner;
	public Entity entityID;
	
	// CONSTRUCTOR
	public MineCar(MCMain plug, Entity id, Player player) {
		this.plugin = plug;
		this.entityID = id;
		this.owner = player;
	}
}
