package com.kshitiz.parking.entities;

public enum Size {
	
	SMALL(1), MEDIUM(2), LARGE(3);

	private int rank;
	
	Size(int rank){
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}

}
