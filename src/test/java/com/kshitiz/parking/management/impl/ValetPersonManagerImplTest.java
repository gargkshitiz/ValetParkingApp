package com.kshitiz.parking.management.impl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.kshitiz.parking.entities.ValetPerson;
import com.kshitiz.parking.exception.ValetPersonUnavailableException;
import com.kshitiz.parking.management.impl.ValetPersonManagerImpl;
public class ValetPersonManagerImplTest {

	private ValetPersonManagerImpl valetPersonManager;
	private ValetPerson valetPerson;
	
	@BeforeMethod
	public void setup(){
		valetPersonManager = new ValetPersonManagerImpl(5);
	}
	
	@Test(expectedExceptions=ValetPersonUnavailableException.class)
	public void shouldThrowUnavailabilityException() throws ValetPersonUnavailableException{
		whenNoValetPersonIsFree().callGetValetPerson();
	}

	@Test
	public void shouldReturnAnAvailableValetPerson() throws ValetPersonUnavailableException{
		callGetValetPerson();
		Assert.assertNotNull(valetPerson);
		Assert.assertTrue(valetPerson.isWorking());
	}
	
	private void callGetValetPerson() throws ValetPersonUnavailableException {
		valetPerson = valetPersonManager.getAvailableValetPerson();
	}

	private ValetPersonManagerImplTest whenNoValetPersonIsFree() {
		for(ValetPerson valetPerson: valetPersonManager.getValetPersons()){
			valetPerson.setWorking(true);
		}
		return this;
	}

}
