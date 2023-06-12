package com.northamptonmedicalclinic.app.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.northamptonmedicalclinic.app.service.DataRepository;

public class TreatmentEditorModel {

	private MedicalStaff user;
	private Appointment appointment;
	private Treatment treatment;
	
	private int index;

	private boolean isNewTreatment;
	private boolean isError;
	private boolean didSet;
	
	private String errorMessage;
	
	
	
	public TreatmentEditorModel(MedicalStaff user,   Appointment appt) {
		this.user = user;
		this.appointment = appt;
		
		isNewTreatment = !tryFindingExistingTreatmentForAppointment();
		if(isNewTreatment)
			treatment = getDefaultValues();
	}
	
	public MedicalStaff getUser() {
		return user;
	}
	
	/**
	 * @return the isError
	 */
	public boolean isError() {
		return isError;
	}



	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}



	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		isError = errorMessage != null;
		this.errorMessage = errorMessage;
	}

	public String getPatientName() {
		return this.appointment.getPatientName();
	}
	
	public String getAppointmentTiming() {
		return this.appointment.getAppointmentDate();
	}


	/**
	 * Returns a default MedicalSatff instance for use with new patient additions.
	 */
	private Treatment getDefaultValues() {
		final Treatment treatment = new Treatment();
		treatment.setAppointmentDate(appointment.getAppointmentDate());
		treatment.setStaffName(user.getName());
		treatment.setTreatment("");
		return treatment;
	}
	
	
	/**
	 * Save the patient information.
	 */
	public void save(Treatment treatment) {
		this.didSet = true;
		this.treatment = treatment;
	}
	
	
	/**
	 * Returns the patient details.
	 */
	public Treatment getDetails() {
		return this.treatment;
	}
	
	/**
	 * Save the patient details, if set, to disk.
	 */
	public void saveToDisk() {
		if(didSet && !isError) {
			final HashMap<String, ArrayList<Treatment>> allTreatments = DataRepository.getInstance().getTreatments(); 
			ArrayList<Treatment> treatments = allTreatments.get(appointment.getPatientID());
			if(treatments == null) {
				treatments = new ArrayList<>();
			}
			
			
			if(isNewTreatment) {
				treatments.add(this.treatment);
			} else {
				treatments.set(this.index, this.treatment);
			}
			allTreatments.put(appointment.getPatientID(), treatments);
			DataRepository.getInstance().saveTreatments(allTreatments);
		}
	}
	
	
	/**
	 * Tries to find an existing treatment for current appointment based on appointment date/time.
	 * If one is found, the treatment and index are set, and the method returns true.
	 */
	private boolean tryFindingExistingTreatmentForAppointment() {
		final HashMap<String, ArrayList<Treatment>> allTreatments = DataRepository.getInstance().getTreatments(); 
		
		ArrayList<Treatment> treatments = allTreatments.get(appointment.getPatientID());
		if(treatments == null)
			return false;
		for (int i = 0; i < treatments.size(); i++) {
			final Treatment treatment = treatments.get(i);
			if(treatment.getAppointmentDate().equals(appointment.getAppointmentDate())) {
				this.treatment = treatment;
				this.index = i;
				return true;
			}
		}
		
		
		return false;
		
	}

	
}
