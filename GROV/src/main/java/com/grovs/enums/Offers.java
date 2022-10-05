package com.grovs.enums;

import org.springframework.stereotype.Component;

public enum Offers {
	
	SUPERSAVE("SUPERSAVE"),
	SAVE10("SAVE10"),
	GROC5("GROC5");

	private String offer;
	private Offers(String offer) {
		// TODO Auto-generated constructor stub
		this.offer=offer;
	}
	
	public String getOffer() {
		return offer;
	}

}
