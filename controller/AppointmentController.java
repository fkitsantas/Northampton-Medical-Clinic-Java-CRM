package com.northamptonmedicalclinic.app.controller;

import com.northamptonmedicalclinic.app.models.AdminRootModel;
import com.northamptonmedicalclinic.app.models.Appointment;
import com.northamptonmedicalclinic.app.models.AppointmentModel;
import com.northamptonmedicalclinic.app.views.AdminView;
import com.northamptonmedicalclinic.app.views.AppointmentView;


public class AppointmentController {

	
	private AppointmentModel model;
	private AppointmentView view;

	public AppointmentController(AppointmentModel model) {
		this.model = model;	
		initialiseModel();
	}
	
	private void initialiseModel() {
		
	}
	
	/**
	 * Set a reference to the AppointmentView.
	 */
	public void setView(AppointmentView view) {
		this.view = view;
	}
	
	
	/**
	 * Return the user back to the Reception Staff Admin menu.
	 */
	public void onCancel() {
		returnToAdmin();
	}
	
	/**
	 * Validate the current appointment, and then save it to disk.
	 */
	public void onSave(Appointment appointment) {
		//Check if the appointment date has not been set. If not, we show an error.
		if(isNullOrEmpty(appointment.getAppointmentDate())) {
			model.setErrorMessage("Appointment date/time cannot be null.");
		} else {
		//Save the current appointment to disk and then return to the admin menu.
			model.setAppointment(appointment);
			model.save();
			returnToAdmin();
		}
	}
	
	/**
	 * Function to check if a given string is either null or empty.
	 */
	private boolean isNullOrEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	
	/**
	 * Returns the user to the reception staff admin menu.
	 */
	private void returnToAdmin() {
		view.dispose();
		AdminRootModel aModel = new AdminRootModel(model.getUser());
		AdminController controller = new AdminController(aModel);
		AdminView aView = new AdminView(controller, aModel);
		controller.setView(aView);
		aView.setVisible(true);
	}
}
