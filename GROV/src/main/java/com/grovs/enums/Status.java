package com.grovs.enums;


public enum Status {
		PENDING("PENDING"),
		SUCCESS("SUCCESS"),
		FAIL("FAIL"),
		CANCELLED("CANCELLED"),
		RETURNED("RETURNED");
	
		private String status;
		
		private Status(String status) {
			this.status=status;
		}
		
		public String getStatus() {
			return status;
		}
}
