package com.northamptonmedicalclinic.app.models;

public class StaffRootModel {

	private MedicalStaff user;

	public StaffRootModel(MedicalStaff currentStaff) {
		this.user = currentStaff;
	}
	
	public MedicalStaff getStaff() {
		return user;
	}
}
