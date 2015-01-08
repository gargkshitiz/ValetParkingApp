package com.kshitiz.parking.management.impl;

import java.util.ArrayList;

import com.kshitiz.parking.entities.ValetPerson;
import com.kshitiz.parking.exception.ValetPersonUnavailableException;
import com.kshitiz.parking.management.ValetPersonManager;

public class ValetPersonManagerImpl implements ValetPersonManager {
	private ArrayList<ValetPerson> valetPersons;

	public ValetPersonManagerImpl(int totalValetPersons){
		this.valetPersons = new ArrayList<ValetPerson>(totalValetPersons);
		for(int i = 0; i < totalValetPersons; i++){
			valetPersons.add(new ValetPerson());
		}
	}
	
	@Override
	public ValetPerson getAvailableValetPerson() throws ValetPersonUnavailableException {
		for(ValetPerson valetPerson:valetPersons){
			if(!valetPerson.isWorking()){
				valetPerson.setWorking(true);
				return valetPerson;
			}
		}
		throw new ValetPersonUnavailableException("All valet persons are busy. Please wait for some time...");
	}
	
	// for unit test
	ArrayList<ValetPerson> getValetPersons() {
		return valetPersons;
	}
}
