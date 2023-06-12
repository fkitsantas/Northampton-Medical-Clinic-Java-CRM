package com.northamptonmedicalclinic.app.models;

import java.util.ArrayList;

import com.northamptonmedicalclinic.app.service.DataRepository;

public class PatientEditorModel {
	private User user;
	private Patient patient;
	
	//
	private boolean isNewPatient;
	
	//Index of the patient details in the array list if we are editing an existing patient.
	private int patientIndex;
	
	private static final String NEW = "Add New Patient";
	private static final String EDIT = "Edit Patient Information";
	private static final String buttonAddStaff = "Add";
	private static final String buttonEditStaff = "Save";
	
	private boolean isError;
	private String errorMessage;
	
	public PatientEditorModel(Patient patient, int patientIndex, User currentuser) {
		this.user = currentuser;
		
		//if patient is null, it means we are adding a new personnel.
		//If we provide a MedicalStaff instance along with the position of the patient in the Array List then we are on edit mode.
		isNewPatient = patient == null;
		
		if(isNewPatient) 
			patient = getDefaultValues();
		this.patient = patient;
		
		this.patientIndex = patientIndex;
		setErrorMessage(null);
	}
	
	public User getUser() {
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



	/**
	 * Returns a default MedicalSatff instance for use with new patient additions.
	 */
	private Patient getDefaultValues() {
		final Patient patient = new Patient("0","", "","","","01/01/2001");
		return patient;
	}
	
	
	/**
	 * Returns the title for the form.
	 */
	public String getTitle() {
		return isNewPatient ? NEW:EDIT;
	}
	
	
	/**
	 * Returns the lable for the save button.
	 */
	public String getButtonText() {
		return isNewPatient ? buttonAddStaff:buttonEditStaff;
	}
	
	
	/**
	 * Is this a new personnel being added?
	 */
	public boolean isNewPatient() {
		return isNewPatient;
	}
	
	/**
	 * Save the patient information.
	 */
	public void save(Patient patient) {
		this.patient = patient;
	}
	
	
	/**
	 * Returns the patient details.
	 */
	public Patient getDetails() {
		return this.patient;
	}
	
	/**
	 * Save the patient details, if set, to disk.
	 */
	public void saveToDisk() {
		if(patient != null) {
			final ArrayList<Patient> patientList = DataRepository.getInstance().getPatientList();
			if(isNewPatient) {
				patientList.add(patient);
			} else {
				patientList.set(patientIndex, patient);
			}
			DataRepository.getInstance().savePatientList(patientList);
		}
	}
	
}
