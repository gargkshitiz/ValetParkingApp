package com.kshitiz.parking.entities;

public class Key {
	
	private int chipNumber;
	private String color; 

	public Key(int chipNumber, String color){
		this.chipNumber = chipNumber;
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chipNumber;
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
		Key other = (Key) obj;
		if (chipNumber != other.chipNumber)
			return false;
		return true;
	}
}
