package com.northamptonmedicalclinic.app.models;

import java.util.ArrayList;

public class TreatmentHistoryModel {
	private MedicalStaff user;
	private String treatmentText;
	private String patientName;
	public TreatmentHistoryModel(MedicalStaff user, ArrayList<Treatment> treatments, String patientName) {
		this.user = user;
		
		treatmentText = compileHistory(treatments);
		this.patientName = patientName;
	}
	
	private String compileHistory(ArrayList<Treatment> treatments) {
		if(treatments == null)
			return "No medical history.";
		
		StringBuilder sb = new StringBuilder();
		for (Treatment treatment : treatments) {
			sb.append("Appointment: ").append(treatment.getAppointmentDate()).append("\n")
			.append("Provider: ").append(treatment.getStaffName()).append("\n\n")
			.append(treatment.getTreatment()).append("\n")
			.append("---------------------------------------").append("\n");
		}
		
		
		return sb.toString();
		
	}
	
	public MedicalStaff getUser() {
		return user;
	}
	
	public String getTreatmentDetails() {
		return treatmentText;
	}
	
	public String getPatientName() {
		return patientName;
	}
}
