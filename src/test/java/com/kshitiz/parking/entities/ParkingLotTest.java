package com.kshitiz.parking.entities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.kshitiz.parking.exception.ParkingSpotAlreadyFilledException;
import com.kshitiz.parking.exception.ParkingSpotUnavailableException;
public class ParkingLotTest {

	private ParkingLot parkingLot;
	private Key key;
	private static final int KEY_CHIP_NUMBER_1 = 1234;
	private static final String KEY_COLOR_1 = "Black";
	private static final String REGISTRATION_NUMBER_1 = "DL8CR1234";
	private Vehicle vehicle;
	private ParkingSpot parkingSpot;
	
	@BeforeMethod
	public void setup(){
		parkingLot = new ParkingLot(10, 20, 20);
		key = new Key(KEY_CHIP_NUMBER_1, KEY_COLOR_1);
		vehicle = new Vehicle(Size.LARGE, REGISTRATION_NUMBER_1, key);
	}
	
	@Test
	public void getParkingSpot() throws ParkingSpotUnavailableException{
		callGetParkingSpotFor(vehicle);
		Assert.assertNotNull(parkingSpot);
	}
	
	@Test(expectedExceptions=ParkingSpotUnavailableException.class)
	public void getParkingSpotWhenNoParkingSpotIsAvailable() throws ParkingSpotUnavailableException, ParkingSpotAlreadyFilledException{
		whenNoParkingSpotIsAvailable().callGetParkingSpotFor(vehicle);
	}
	
	@Test(expectedExceptions=ParkingSpotUnavailableException.class)
	public void tryParkingALargeVehicleWhenParkingLotDoesNotHaveALargeSpotAvailable() throws ParkingSpotUnavailableException{
		parkingLot = new ParkingLot(10, 20, 0);
		parkingLot.getParkingSpotFor(vehicle);
	}

	private void callGetParkingSpotFor(Vehicle vehicle) throws ParkingSpotUnavailableException {
		parkingSpot = parkingLot.getParkingSpotFor(vehicle);
	}

	private ParkingLotTest whenNoParkingSpotIsAvailable() throws ParkingSpotAlreadyFilledException {
		for(ParkingSpot parkingSpot: parkingLot.getParkingSpots()){
			parkingSpot.park(vehicle);
		}
		return this;
	}
}
