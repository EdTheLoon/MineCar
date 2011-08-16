package com.edtheloon.MineCar;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

public abstract class MineCar implements Minecart {
	
	// Class variables	
	public Player owner;
	public Entity entityID;
	
	// CONSTRUCTOR
	public MineCar(Entity id, Player player) {
		this.entityID = id;
		this.owner = player;
	}
}
