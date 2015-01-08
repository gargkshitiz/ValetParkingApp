package com.kshitiz.parking.entities;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VehicleTest {
	
	private static final String PARKING_SPOT_NUMBER = "B0";
	private Key key1;
	private static final String REGISTRATION_NUMBER_1 = "DL8CR1234";
	private static final int KEY_CHIP_NUMBER_1 = 1234;
	private static final String KEY_COLOR_1 = "Black";
	private static final int KEY_CHIP_NUMBER_2 = 5678;
	private static final String KEY_COLOR_2 = "Brown";
	private Key key2;
	private static final String REGISTRATION_NUMBER_2 = "DL8CR5678";
	private Vehicle vehicle1;
	private Size size1;
	private Vehicle vehicle2;
	private ParkingSpot parkingSpot;
	private Size size2;
	private boolean canFitAt;
	
	@BeforeMethod
	public void setup(){
		size1 = Size.SMALL;
		size2 = Size.MEDIUM;
		key1 = new Key(KEY_CHIP_NUMBER_1, KEY_COLOR_1);
		key2 = new Key(KEY_CHIP_NUMBER_2, KEY_COLOR_2);
		vehicle1 = new Vehicle(Size.SMALL, REGISTRATION_NUMBER_1, key1);
		vehicle2 = new Vehicle(Size.MEDIUM, REGISTRATION_NUMBER_1, key2);
	}
	
	@Test
	public void equals(){
		Assert.assertEquals(vehicle1, vehicle2);
	}
	
	@Test
	public void unEqual(){
		vehicle2 = new Vehicle(size2, REGISTRATION_NUMBER_2, key2);
		Assert.assertNotEquals(vehicle1, vehicle2);
	}
	
	@Test
	public void canFitAt(){
		whenParkingSpotIsBigEnough().callCanFitAt();
		Assert.assertEquals(canFitAt, true);
	}

	@Test
	public void canNotFitAt(){
		whenParkingSpotIsSmall().callCanFitAt();
		Assert.assertEquals(canFitAt, false);
	}

	private VehicleTest whenParkingSpotIsSmall() {
		parkingSpot = new ParkingSpot(size1, PARKING_SPOT_NUMBER);
		return this;
	}

	private void callCanFitAt() {
		canFitAt = vehicle2.canFitAt(parkingSpot);
	}

	private VehicleTest whenParkingSpotIsBigEnough() {
		parkingSpot = new ParkingSpot(size2, PARKING_SPOT_NUMBER);
		return this;
	}
}
