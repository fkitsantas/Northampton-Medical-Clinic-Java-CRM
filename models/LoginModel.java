package com.northamptonmedicalclinic.app.models;

public class LoginModel {
	private User currentUser;
	private static final String errorMessage = "Invalid user name or password.";
	private boolean isError;

	
	public LoginModel() {
		currentUser = null;
		isError = false;
	}
		
	
	
	public boolean isLoggedIn() {
		return currentUser != null;
	}

	public void login(User user) {
		this.currentUser = user;
		isError = false;
	}
	
	public void logout() {
		currentUser = null;
	}
	
	
	public String getError() {
		return isError ? errorMessage:null;
	}
	
	public boolean isError() {
		return this.isError;
	}
	
	public void setError(boolean isError) {
		this.isError = isError;
	}
	
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Check if the currentUser is an instance of MedicalStaff and return an instance of MedicalStaff. We can do this by checking that
	 * the current user is not a reception staff, and the forcefully casting it as MedicalStaff. ITs a bit brute force but it simplefies
	 * login management.
	 */
	public MedicalStaff getAsMedicalStaff() {
		if(currentUser != null && !currentUser.isReceptionStaff())
			return (MedicalStaff) currentUser;
		return null;
	}
}
