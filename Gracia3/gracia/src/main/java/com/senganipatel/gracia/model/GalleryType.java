package com.senganipatel.gracia.model;

public enum GalleryType {

	LONG(0),NATURAL(6),ROUGHCUT(21),SMOKED(35),ALL(0), FAVORITE(9),NEW_COLLECTION(52);
	
	private GalleryType(int startIndex){
		this.startIndex = startIndex;
	}
	
	private int startIndex;
	
	public int startIndex() {
		return startIndex;
	}
	
	
}
