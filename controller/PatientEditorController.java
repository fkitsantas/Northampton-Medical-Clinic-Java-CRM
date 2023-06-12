package com.northamptonmedicalclinic.app.controller;

import java.util.ArrayList;

import com.northamptonmedicalclinic.app.models.AdminRootModel;
import com.northamptonmedicalclinic.app.models.Patient;
import com.northamptonmedicalclinic.app.models.PatientEditorModel;
import com.northamptonmedicalclinic.app.service.DataRepository;
import com.northamptonmedicalclinic.app.views.AdminView;
import com.northamptonmedicalclinic.app.views.PatientEditorView;


/**
 * Controller class that handles saving a patient information.
 */
public class PatientEditorController  {
	private PatientEditorModel model;
	private PatientEditorView view;
	
	public PatientEditorController(PatientEditorModel model) {
		this.model = model;
	}
	
	/**
	 * Set the current view reference for PatientEditorView.
	 */
	public void setView(PatientEditorView view) {
		this.view = view;
	}
	
	/**
	 * Validate and save the patient to disk.
	 */
	public void onSave(Patient patient) {
		final String errorMessage = validate(patient);
		model.setErrorMessage(errorMessage);
		
		if(errorMessage == null) {
			model.setErrorMessage(null);
			model.save(patient);
			model.saveToDisk();
			returnToAdminMenu();
		}
		
		if(model.isError()) {
			view.update();
		}
	}
	
	/**
	 * Validate the patient details to make sure that relevant fields are not empty.
	 */
	private String validate(Patient patient) {
		if(isNullOrEmpty(patient.getId())) {
			return "ID name cannot be empty";
		} else if(isNullOrEmpty(patient.getName())) {
			return "Name cannot be empty";
		} else if (isNullOrEmpty(patient.getEmail())) {
			return "Email cannot be empty";
		} else if (isNullOrEmpty(patient.getPhone())) {
			return "Phone date cannot empty";
		} else if (isNullOrEmpty(patient.getAddress())) {
			return "Address cannot empty";
		} else if(model.isNewPatient() && isDuplicateUser(patient.getId())){
			return "A patient already exists for this id.";
		} 
		return null;
	}
	
	
	/**
	 * Check if a patient already exists for the given id.
	 */
	private boolean isDuplicateUser(final String id) {
		ArrayList<Patient> patients = DataRepository.getInstance().getPatientList();
		for (Patient patient : patients) {
			if(patient.getId().equalsIgnoreCase(id))
				return true;
		}
		return false;
	}
	
	/**
	 * Utility method to check if a string is either null or empty.
	 */
	private boolean isNullOrEmpty(String value) {
		return (value == null || value.length() == 0);
	}
	
	
	
	/**
	 * Return the user to the reception staff menu.
	 */
	public void onCancel() {
		returnToAdminMenu();
	}
	
	
	/**
	 * Return the user to reception staff menu.
	 */
	private void returnToAdminMenu() {
		AdminRootModel aModel = new AdminRootModel(model.getUser());
		AdminController controller = new AdminController(aModel);
		AdminView aView = new AdminView(controller, aModel);
		controller.setView(aView);
		aView.setVisible(true);
		view.dispose();
		
	}
}
