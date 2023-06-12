package com.northamptonmedicalclinic.app.controller;

import java.util.ArrayList;

import com.northamptonmedicalclinic.app.models.AdminRootModel;
import com.northamptonmedicalclinic.app.models.MedicalStaff;
import com.northamptonmedicalclinic.app.models.StaffEditorModel;
import com.northamptonmedicalclinic.app.service.DataRepository;
import com.northamptonmedicalclinic.app.views.AdminView;
import com.northamptonmedicalclinic.app.views.StaffEditorView;

public class StaffEditorController  {
	private StaffEditorModel model;
	private StaffEditorView view;
	
	public StaffEditorController(StaffEditorModel model) {
		this.model = model;
	}
	
	/**
	 * Store an instance of StaffEditorView.
	 */
	public void setView(StaffEditorView view) {
		this.view = view;
	}
	
	
	/**
	 * Save the medical staff details to disk
	 */
	public void onSave(MedicalStaff staff) {
		final String error = validate(staff);
		model.setErrorMessage(error);

		if(error == null) {
			model.saveStaff(staff);
			model.saveToDisk();
			returnToAdminMenu();
		} else {
			view.update();
		}
	}
	
	/**
	 * Validate the new medical staff details to make sure no fields are empty.
	 */
	private String validate(MedicalStaff staff) {
		if(isNullOrEmpty(staff.getUsername())) {
			return "User name cannot be empty";
		} else if(isNullOrEmpty(staff.getPassword())) {
			return "Password cannot be empty";
		} else if (isNullOrEmpty(staff.getName())) {
			return "Name cannot be empty";
		} else if (isNullOrEmpty(staff.getQualificationDate())) {
			return "Qualification date cannot empty";
		} else if (isNullOrEmpty(staff.getRegistrationDate())) {
			return "Registration date cannot empty";
		} else if (isNullOrEmpty(staff.getRegistrationNumber())) {
			return "Registration number cannot empty";
		} else if (isNullOrEmpty(staff.getGrade()) && staff.isNurse()) {
			return "Nurse grade cannot empty";
		} else if(model.isNewStaff() && isDuplicateUser(staff.getUsername())){
			return "A user already exists for this username.";
		}
		return null;
	}
	
	
	/**
	 * Check if a staff already exists for a given username.
	 */
	private boolean isDuplicateUser(final String user) {
		ArrayList<MedicalStaff> staffList = DataRepository.getInstance().getStaffList();
		for (MedicalStaff medicalStaff : staffList) {
			if(medicalStaff.getUsername().equalsIgnoreCase(user))
				return true;
		}
		return false;
	}
	
	/**
	 * Utility method to check if a given string is empty or null.
	 */
	private boolean isNullOrEmpty(String value) {
		return (value == null || value.length() == 0);
	}
	
	
	/**
	 * Returns the user to reception staff root menu.
	 */
	public void onCancel() {
		returnToAdminMenu();
	}
	
	/**
	 * Returns the user to reception staff root menu.
	 */
	private void returnToAdminMenu() {
		view.dispose();
		AdminRootModel aModel = new AdminRootModel(model.getUser());
		AdminController controller = new AdminController(aModel);
		AdminView aView = new AdminView(controller, aModel);
		controller.setView(aView);
		aView.setVisible(true);
	}
}
