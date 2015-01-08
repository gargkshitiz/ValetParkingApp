package com.kshitiz.parking.entities;

import com.kshitiz.parking.management.ValetParkingManager;

public class Customer {
	
	private ValetToken valetToken;
	private ValetParkingManager valetParkingManager;
	private Vehicle vehicle;
	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setValetToken(ValetToken valetToken) {
		this.valetToken = valetToken;
	}
	
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void setValetParkingManager(ValetParkingManager valetParkingManager) {
		this.valetParkingManager = valetParkingManager;
	}

	public ValetToken getValetToken() {
		return valetToken;
	}
}
