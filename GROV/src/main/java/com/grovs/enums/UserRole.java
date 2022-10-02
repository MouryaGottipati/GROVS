package com.grovs.enums;

public enum UserRole {

	
		USER("USER"),
		ADMIN("ADMIN");
		
		private String userRole;

		private UserRole(String userRole) {
			this.userRole = userRole;
		}

		public String getUserRole() {
			return userRole;
		}
		
	

}
