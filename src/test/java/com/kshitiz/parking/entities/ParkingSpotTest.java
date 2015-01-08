package com.kshitiz.parking.entities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.kshitiz.parking.exception.IllegalVehicleKeyException;
import com.kshitiz.parking.exception.ParkingSpotAlreadyFilledException;
import com.kshitiz.parking.exception.ParkingSpotUnavailableException;
import com.kshitiz.parking.exception.TheftDetectedException;
public class ParkingSpotTest {
	private static final String PARKING_SPOT_NUMBER_1 = "B10";
	private static final String PARKING_SPOT_NUMBER_2 = "B11";
	private Key key1;
	private Key key2;
	private static final int KEY_CHIP_NUMBER_1 = 1234;
	private static final String KEY_COLOR_1 = "Black";
	private static final String REGISTRATION_NUMBER_1 = "DL8CR1234";
	private Vehicle vehicle1;
	private Vehicle vehicle2;
	private static final int KEY_CHIP_NUMBER_2 = 5678;
	private static final String KEY_COLOR_2 = "Brown";
	private static final String REGISTRATION_NUMBER_2 = "DL8CR5678";
	private ParkingSpot parkingSpot1;
	private ParkingSpot parkingSpot2;
	
	@BeforeMethod
	public void setup(){
		parkingSpot1 = new ParkingSpot(Size.SMALL, PARKING_SPOT_NUMBER_1);
		parkingSpot2 = new ParkingSpot(Size.LARGE, PARKING_SPOT_NUMBER_1);
		key1 = new Key(KEY_CHIP_NUMBER_1, KEY_COLOR_1);
		key2 = new Key(KEY_CHIP_NUMBER_2, KEY_COLOR_2);
		vehicle1 = new Vehicle(Size.SMALL, REGISTRATION_NUMBER_1, key1);
		vehicle2 = new Vehicle(Size.MEDIUM, REGISTRATION_NUMBER_2, key2);
	}
	
	@Test
	public void equals(){
		Assert.assertEquals(parkingSpot1, parkingSpot2);
	}
	
	@Test
	public void unEqual(){
		parkingSpot2 = new ParkingSpot(Size.LARGE, PARKING_SPOT_NUMBER_2);
		Assert.assertNotEquals(parkingSpot1, parkingSpot2);
	}
	
	@Test
	public void isFreeWhenOneVehicleIsAlreadyParkedAtTheSpot() throws ParkingSpotAlreadyFilledException{
		parkingSpot1.park(vehicle1);
		Assert.assertFalse(parkingSpot1.isFree());
	}

	@Test(expectedExceptions=ParkingSpotAlreadyFilledException.class)
	public void parkOnTheSameSpotWhenOneVehicleIsAlreadyParkedAtTheSpot() throws ParkingSpotAlreadyFilledException{
		parkingSpot1.park(vehicle1);
		parkingSpot1.park(vehicle2);
	}
	
	@Test
	public void getVehicleBackAfterParkingIt() throws TheftDetectedException, IllegalVehicleKeyException, ParkingSpotAlreadyFilledException{
		parkingSpot1.park(vehicle1);
		Vehicle vehicle = parkingSpot1.getVehicle(key1);
		Assert.assertEquals(vehicle,vehicle1);
	}
	
	@Test(expectedExceptions=IllegalVehicleKeyException.class)
	public void tryGettingVehicleBackWithIncorrectKey() throws TheftDetectedException, IllegalVehicleKeyException, ParkingSpotAlreadyFilledException{
		parkingSpot1.park(vehicle1);
		parkingSpot1.getVehicle(key2);
	}
	
	@Test(expectedExceptions=TheftDetectedException.class)
	public void tryGettingStolenVehicleBackWithCorrectKey() throws TheftDetectedException, IllegalVehicleKeyException, ParkingSpotAlreadyFilledException{
		parkingSpot1.park(vehicle1);
		parkingSpot1.stealVehicle();
		parkingSpot1.getVehicle(key1);
	}
}
