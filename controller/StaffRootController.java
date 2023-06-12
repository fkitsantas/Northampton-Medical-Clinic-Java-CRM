package com.northamptonmedicalclinic.app.controller;

import com.northamptonmedicalclinic.app.models.LoginModel;
import com.northamptonmedicalclinic.app.models.StaffRootModel;
import com.northamptonmedicalclinic.app.models.TreatmentSelectionModel;
import com.northamptonmedicalclinic.app.views.LoginView;
import com.northamptonmedicalclinic.app.views.SelectionView;
import com.northamptonmedicalclinic.app.views.StaffRootView;

public class StaffRootController {

	private StaffRootModel model;
	private StaffRootView view;

	public StaffRootController(StaffRootModel model) {
		this.model = model;
	}
	
	public void setView(StaffRootView view) {
		this.view = view;
	}
	
	/**
	 * Show appointment selection UI. From there the medical staff can record a new treatment for a patient.
	 */
	public void onClickNew() {
		final TreatmentSelectionModel tModel = 
				new TreatmentSelectionModel("Appointments", "Add Treatment", "Select Appointment");
		tModel.setUser(model.getStaff());
		final NewTreatmentSelectionController controller = new NewTreatmentSelectionController(tModel);
		final SelectionView sView = new SelectionView(tModel, controller);
		controller.setView(sView);
		sView.setVisible(true);
		view.dispose();
	}
	
	/**
	 * Show a list of patients for which the medical staff can view patient history.
	 */
	public void onClickViewTreatments() {
		final TreatmentSelectionModel tModel = 
				new TreatmentSelectionModel("Treatment History", "View", "Select Patient");
		tModel.setUser(model.getStaff());
		final TreatmentHistorySelectionController controller = new TreatmentHistorySelectionController(tModel);
		final SelectionView sView = new SelectionView(tModel, controller);
		controller.setView(sView);
		sView.setVisible(true);
		view.dispose();
	}
	
	/**
	 * Return to login screen.
	 */
	public void onClickLogOut() {
		final LoginModel model = new LoginModel();
		final LoginController controller = new LoginController(model);
		final LoginView loginView = new LoginView(controller, model);
		controller.setView(loginView);
		loginView.setVisible(true);
		view.dispose();
	}
}
