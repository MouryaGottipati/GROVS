package com.grovs.enums;


public enum PaymentMode{
	
		CASH("CASH"),
		CARD("CARD");
		
	
		private String paymentMode;
		
		private PaymentMode(String paymentMode) {
			this.paymentMode=paymentMode;
		}
		
		public String getPaymentMode() {
			return paymentMode;
		}
}
