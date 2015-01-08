package com.kshitiz.parking.entities;

public class ValetToken {
	
	private int number;
	private boolean isAssigned;
	
	public boolean isAssigned() {
		return isAssigned;
	}

	public void setAssigned(boolean isAssigned) {
		this.isAssigned = isAssigned;
	}

	public ValetToken(int number){
		this.number = number;	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
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
		ValetToken other = (ValetToken) obj;
		if (number != other.number)
			return false;
		return true;
	}
}
