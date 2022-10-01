package com.grovs.enums;


public enum CartUserType {
	EXISTING_USER("EXISTING_USER"),
	ANONYMOUS_USER("ANONYMUS_USER");
	
	private String cartUserType;

	private CartUserType(String cartUserType) {
		this.cartUserType = cartUserType;
	}

	public String getCartUserType() {
		return cartUserType;
	}
	
}
