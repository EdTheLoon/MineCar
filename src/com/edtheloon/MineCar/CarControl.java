package com.edtheloon.MineCar;

import org.bukkit.entity.Vehicle;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.player.SpoutPlayer;

public class CarControl {
	
	public static void moveForward(SpoutPlayer player) {
		// TODO: ADD MOVEMENT CODE
		Vehicle cart = player.getVehicle();
		Vector vel = cart.getVelocity();
		
		vel.setX(1);
		cart.setVelocity(vel);
	}
	
	public static void moveBackward(SpoutPlayer player) {
		// TODO: ADD MOVEMENT CODE
		Vehicle cart = player.getVehicle();
		Vector vel = cart.getVelocity();
		
		vel.setX(-1);
		cart.setVelocity(vel);
	}
	
	public static void turn(SpoutPlayer player, String direction) {
		// TODO: ADD TURNING CODE
		Vehicle cart = player.getVehicle();
		Vector vel = cart.getVelocity();
		
		if (direction == "left") vel.setY(0.5); else vel.setY(-0.5);
		cart.setVelocity(vel);
	}

}
