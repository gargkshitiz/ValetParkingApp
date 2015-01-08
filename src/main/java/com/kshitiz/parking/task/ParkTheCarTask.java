package com.kshitiz.parking.task;

import com.kshitiz.parking.entities.ParkingSpot;
import com.kshitiz.parking.entities.ValetPerson;
import com.kshitiz.parking.entities.Vehicle;
import com.kshitiz.parking.exception.ParkingSpotAlreadyFilledException;

public class ParkTheCarTask implements Runnable {

	private Vehicle vehicle;
	private ParkingSpot parkingSpot;
	private ValetPerson valetPerson;

	public ParkTheCarTask(ValetPerson valetPerson, Vehicle vehicle, ParkingSpot parkingSpot) {
		this.valetPerson = valetPerson;
		this.vehicle = vehicle;
		this.parkingSpot = parkingSpot;
	}

	@Override
	public void run() {
		try {
			valetPerson.park(vehicle, parkingSpot);
		} 
		catch (ParkingSpotAlreadyFilledException e) {
			// TODO: Assigned parking spot is not correct. Talk to ValetParkingManager;
		}
	}

}
