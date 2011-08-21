package com.edtheloon.MineCar;

import org.bukkit.entity.Minecart;
import org.bukkit.util.Vector;
import org.getspout.spoutapi.player.SpoutPlayer;

// TODO: ADD REAL CAR DRIVING PHYSICS...

public class CarControl {

	public static void moveForward(SpoutPlayer player) {
		// TODO: ADD MOVEMENT CODE
		Minecart cart = (Minecart) player.getVehicle();
		Vector vel = cart.getVelocity();

		vel.setX(1);
		cart.setVelocity(vel);
	}

	public static void moveBackward(SpoutPlayer player) {
		// TODO: ADD MOVEMENT CODE
		Minecart cart = (Minecart) player.getVehicle();
		Vector vel = cart.getVelocity();

		vel.setX(-1);
		cart.setVelocity(vel);
	}

	public static void turn(SpoutPlayer player, String direction) {
		// TODO: ADD TURNING CODE
		Minecart cart = (Minecart) player.getVehicle();
		Vector vel = cart.getVelocity();

		if (direction == "left") vel.setZ(0.5); else vel.setZ(-0.5);
		cart.setVelocity(vel);
	}

}
