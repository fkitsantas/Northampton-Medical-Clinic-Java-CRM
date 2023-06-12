package com.northamptonmedicalclinic.app.models;

public class TreatmentSelectionModel extends SelectionModel<Appointment>{
	private MedicalStaff user;
	
	public TreatmentSelectionModel(String title, String actionButtonLabel, String selectionLabel) {
		super(title, actionButtonLabel, selectionLabel, null);
	}
	
	public void setUser(MedicalStaff user) {
		this.user = user;
	}
	
	public MedicalStaff getUser() {
		return user;
	}
	


}
