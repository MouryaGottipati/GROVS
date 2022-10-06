package com.grovs.enums;


public enum PaymentType {
	
		CASH("CASH"),
		DEBIT_CARD("DEBIT_CARD"),
		CREDIT_CARD("CREDIT_CARD");
	
		private String paymentType;
		
		private PaymentType(String paymentType) {
			this.paymentType=paymentType;
		}
		
		public String getType() {
			return paymentType;
		}
}
