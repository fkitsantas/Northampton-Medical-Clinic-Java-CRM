package com.northamptonmedicalclinic.app.models;

public class User {
	private String username;
	private String password;
	
	protected int staffType;
	
	public final static int STAFF_RECEPTION_ADMIN = 0;
	public final static int STAFF_DOCTOR = 1;
	public final static int STAFF_NURSE = 2;
	
	public User(String user, String password) {
		this.username = user;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	
	/**
	 * @param staffType the staffType to set
	 */
	public void setStaffType(int staffType) {
		this.staffType = staffType;
	}
	
	
	public String getStaffType() {
		switch(staffType) {
		case STAFF_RECEPTION_ADMIN: return "Reception Staff";
		case STAFF_DOCTOR: return "Doctor";
		default: return "Nurse";
		}
	}
	
	public boolean isNurse() {
		return this.staffType == STAFF_NURSE;
	}
	
	public boolean isDoctor() {
		return this.staffType == STAFF_DOCTOR;
	}
	
	public boolean isReceptionStaff() {
		return this.staffType == STAFF_RECEPTION_ADMIN;
	}
	
	public boolean login(final String username, final String password) {
		return this.username.equals(username) && this.password.equals(password);
	}
	
	
}
