package com.northamptonmedicalclinic.app.controller;

import com.northamptonmedicalclinic.app.models.AdminRootModel;
import com.northamptonmedicalclinic.app.models.AppointmentModel;
import com.northamptonmedicalclinic.app.models.LoginModel;
import com.northamptonmedicalclinic.app.models.MedicalStaff;
import com.northamptonmedicalclinic.app.models.Patient;
import com.northamptonmedicalclinic.app.models.PatientEditorModel;
import com.northamptonmedicalclinic.app.models.SelectionModel;
import com.northamptonmedicalclinic.app.models.StaffEditorModel;
import com.northamptonmedicalclinic.app.views.AdminView;
import com.northamptonmedicalclinic.app.views.AppointmentView;
import com.northamptonmedicalclinic.app.views.LoginView;
import com.northamptonmedicalclinic.app.views.PatientEditorView;
import com.northamptonmedicalclinic.app.views.SelectionView;
import com.northamptonmedicalclinic.app.views.StaffEditorView;


/**
 * Handles the button click events from the AdminView.
 *
 */
public class AdminController {
	
	
	
	private AdminView view;
	private AdminRootModel model;
	
	public AdminController(AdminRootModel model) {
		this.model = model;
	}
	
	/**
	 * Set a reference to the current view.
	 */
	public void setView(AdminView view) {
		this.view = view;
	}
	
	/**
	 * Log the user out.
	 */
	public void logOut() {
		final LoginModel model = new LoginModel();
		final LoginController  controller = new LoginController(model);
		final LoginView loginView = new LoginView(controller, model);
		controller.setView(loginView);
		loginView.setVisible(true);
		view.dispose();
	}
	
	/**
	 * Show the form to add a new medical staff.
	 */
	public void onClickAddStaff() {
		final StaffEditorModel model = new StaffEditorModel(null, -1, this.model.getUser());
		final StaffEditorController controller = new StaffEditorController(model);
		final StaffEditorView editorView = new StaffEditorView(controller, model);
		controller.setView(editorView);
		editorView.setVisible(true);
		view.dispose();
	}
	
	/**
	 * Show a list of staff to delete from the records.
	 */
	public void onClickDeleteStaff() {
		final SelectionModel<MedicalStaff> model = new SelectionModel<>("Delete Staff", "Delete", "Name", this.model.getUser());
		final BaseSelectionController controller = new StaffDeleteController(model);
		final SelectionView mView = new SelectionView(model,controller);
		controller.setView(mView);
		mView.setVisible(true);
		view.dispose();
	}
	
	/**
	 * Presents a list of current medical staff from which the reception staff can choose.  
	 * They can then edit the details of the selected medical staff.
	 */
	public void onClickEditStaff() {
		final SelectionModel<MedicalStaff> model = new SelectionModel<>("Edit Staff", "Edit", "Name", this.model.getUser());
		final BaseSelectionController controller = new StaffSelectionController(model);
		final SelectionView mView = new SelectionView(model,controller);
		controller.setView(mView);
		mView.setVisible(true);
		view.dispose();
	}
	
	
	/**
	 * Add new patient in the system.
	 */
	public void onClickAddPatient() {
		final PatientEditorModel model = new PatientEditorModel(null, -1, this.model.getUser());
		final PatientEditorController controller = new PatientEditorController(model);
		final PatientEditorView editorView = new PatientEditorView(controller, model);
		controller.setView(editorView);
		editorView.setVisible(true);
		view.dispose();
	}
	
	
	/**
	 * Show a list of current patients for whom the details need to be edited.
	 */
	public void onClickEditPatient() {
		final SelectionModel<Patient> model = new SelectionModel<>("Edit Patient", "Edit", "Name", this.model.getUser());
		final BaseSelectionController controller = new PatientSelectionController(model);
		final SelectionView mView = new SelectionView(model,controller);
		controller.setView(mView);
		mView.setVisible(true);
		view.dispose();
	}
	
	/**
	 * Show a list of currently registered patient with the option to delete.
	 */
	public void onClickDeletePatient() {
		final SelectionModel<Patient> model = new SelectionModel<>("Delete Patient", "Delete", "Name", this.model.getUser());
		final BaseSelectionController controller = new PatientDeleteController(model);
		final SelectionView mView = new SelectionView(model,controller);
		controller.setView(mView);
		mView.setVisible(true);
		view.dispose();
	}
	
	
	/**
	 * Show the new appointment interface.
	 */
	public void onClickAddAppointment() {
		final AppointmentModel model = new AppointmentModel(this.model.getUser());
		final AppointmentController controller = new AppointmentController(model);
		final AppointmentView mView = new AppointmentView(controller, model);
		controller.setView(mView);
		mView.setVisible(true);
		view.dispose();
	}

	
}
