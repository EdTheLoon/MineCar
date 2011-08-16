package com.edtheloon.MineCar;

import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;

public abstract class MineCar implements Minecart {
	
	// Class variables	
	@SuppressWarnings("unused")
	private MCMain plugin; // This may not be needed. If removed just comment it out and alter the constructor
	public Player owner;
	public Minecart minecart;
	
	// CONSTRUCTOR
	public MineCar(MCMain plug, Minecart cart, Player player) {
		this.plugin = plug;
		this.minecart = cart;
		this.owner = player;
	}
}
