package com.kshitiz.parking.integration;

import org.testng.Assert;

import com.kshitiz.parking.entities.Customer;
import com.kshitiz.parking.entities.Key;
import com.kshitiz.parking.entities.ParkingLot;
import com.kshitiz.parking.entities.Size;
import com.kshitiz.parking.entities.ValetToken;
import com.kshitiz.parking.entities.Vehicle;
import com.kshitiz.parking.exception.IllegalValetTokenException;
import com.kshitiz.parking.exception.ParkingSpotUnavailableException;
import com.kshitiz.parking.exception.TheftDetectedException;
import com.kshitiz.parking.exception.ValetPersonUnavailableException;
import com.kshitiz.parking.exception.ValetTokenExhaustedException;
import com.kshitiz.parking.management.ValetParkingManager;
import com.kshitiz.parking.management.impl.ValetParkingManagerImpl;
import com.kshitiz.parking.management.impl.ValetPersonManagerImpl;
import com.kshitiz.parking.management.impl.ValetTokenManagerImpl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ParkingAppIntegrationTest {

	private static final int KEY_CHIP_NUMBER_1 = 1234;
	private static final int KEY_CHIP_NUMBER_2 = 5678;
	private static final String KEY_COLOR_1 = "Black";
	private static final String KEY_COLOR_2 = "Brown";
	private static final String REGISTRATION_NUMBER_1 = "DL8CR1234";
	private static final String REGISTRATION_NUMBER_2 = "DL8CR5678";
	private ValetPersonManagerImpl valetPersonManager;
	private ValetTokenManagerImpl valetTokenManager;
	private ValetParkingManager valetParkingManager;
	private Customer customer1;
	private Customer customer2;
	
	@BeforeMethod
	private void setup() {
		ParkingLot parkingLot = new ParkingLot(10,20,20);
		valetPersonManager = new ValetPersonManagerImpl(10);
		valetTokenManager = new ValetTokenManagerImpl(50);
		valetParkingManager = new ValetParkingManagerImpl(parkingLot , valetPersonManager, valetTokenManager);
		customer1 = new Customer();
		customer1.setValetParkingManager(valetParkingManager);
		customer2 = new Customer();
		customer2.setValetParkingManager(valetParkingManager);
	}

	@Test
	public void verifyParkingAppIsWorkingFine() throws InterruptedException, ValetTokenExhaustedException, 
							ValetPersonUnavailableException, ParkingSpotUnavailableException, TheftDetectedException, IllegalValetTokenException{
		
		setCustomerVehicleDetails(customer1, KEY_CHIP_NUMBER_1,KEY_COLOR_1, Size.SMALL,REGISTRATION_NUMBER_1 );
		setCustomerVehicleDetails(customer2, KEY_CHIP_NUMBER_2,KEY_COLOR_2, Size.MEDIUM,REGISTRATION_NUMBER_2 );
		
		ValetToken valetToken1 = valetParkingManager.park(customer1.getVehicle());
		ValetToken valetToken2 = valetParkingManager.park(customer2.getVehicle());
		// Waiting (10 seconds) for the car to get parked. 
		// TODO: Bad way to test as sleep is not recommended in a test case
		Thread.sleep(5000);
		runSanityCheckForCustomer(valetParkingManager, customer1, valetToken1);
		runSanityCheckForCustomer(valetParkingManager, customer2, valetToken2);
	}

	private void runSanityCheckForCustomer(ValetParkingManager valetParkingManager, Customer customer, ValetToken valetToken)
			throws TheftDetectedException, ValetPersonUnavailableException, IllegalValetTokenException {
		Vehicle receivedVehicle = valetParkingManager.getVehicle(valetToken);
		Assert.assertEquals(receivedVehicle, customer.getVehicle());
		Assert.assertEquals(receivedVehicle.getKey(),  customer.getVehicle().getKey());
		Assert.assertEquals(receivedVehicle.getSize(),  customer.getVehicle().getSize());
	}

	private void setCustomerVehicleDetails(Customer customer, int chipNumber, 
			String keyColor, Size vehicleSize, String vehicleRegistrationNumber) throws 
					ValetTokenExhaustedException, ValetPersonUnavailableException, ParkingSpotUnavailableException {
		Key key = new Key(chipNumber, keyColor);
		Vehicle vehicle = new Vehicle(vehicleSize, vehicleRegistrationNumber, key);
		customer.setVehicle(vehicle);
	}

}
