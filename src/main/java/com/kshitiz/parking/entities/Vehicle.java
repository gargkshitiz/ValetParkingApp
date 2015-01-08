package com.kshitiz.parking.entities;


public class Vehicle {

	private Size size;
	private String registrationNumber;
	private Key key;
	
	public Vehicle(Size size, String registrationNumber, Key key) {
		this.size = size;
		this.registrationNumber = registrationNumber;
		this.key = key;
	}
	
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public Size getSize() {
		return size;
	}

	public boolean canFitAt(ParkingSpot parkingSpot) {
		if(this.size.getRank()<=parkingSpot.getSize().getRank()){
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((registrationNumber == null) ? 0 : registrationNumber
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (registrationNumber == null) {
			if (other.registrationNumber != null)
				return false;
		} else if (!registrationNumber.equals(other.registrationNumber))
			return false;
		return true;
	}

	public Key getKey() {
		return key;
	}

}
