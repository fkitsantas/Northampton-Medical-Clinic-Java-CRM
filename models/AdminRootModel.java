package com.northamptonmedicalclinic.app.models;

public class AdminRootModel {
	/**
	 * Stores an instance of currently logged in user. 
	 * It is used by the view to show the currently logged in details.
	 */
	private User user;
	
	public AdminRootModel(User user) {
		this.user = user;
	}
	
	/**
	 * Return the currently logged in user.
	 */
	public User getUser() {
		return user;
	}
}
