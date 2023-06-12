package com.northamptonmedicalclinic.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.northamptonmedicalclinic.app.models.Appointment;
import com.northamptonmedicalclinic.app.models.StaffRootModel;
import com.northamptonmedicalclinic.app.models.TreatmentEditorModel;
import com.northamptonmedicalclinic.app.models.TreatmentSelectionModel;
import com.northamptonmedicalclinic.app.service.DataRepository;
import com.northamptonmedicalclinic.app.views.SelectionView;
import com.northamptonmedicalclinic.app.views.StaffRootView;
import com.northamptonmedicalclinic.app.views.TreatmentEditorView;

/**
 * Controller class to add new treatment for a patient for a given appointment.
 */
public class NewTreatmentSelectionController extends BaseSelectionController {

	private TreatmentSelectionModel model;
	private SelectionView view;

	public NewTreatmentSelectionController(TreatmentSelectionModel model) {
		this.model = model;
		initializeModel();
	}
	
	
	/**
	 * Initializes the model. It performs two actions:
	 * 1) A list of appointments for the current medical staff is stored in the model.
	 * 2) A String array is populated in the model which is used by the view to display the ComboBox choices. 
	 */
	private void initializeModel() {
		
		//Get a list of all appointments for the current user.
		ArrayList<Appointment> appointments = getAppointmentsForCurrentStaff(); 
		model.setList(appointments);
		
		//Check if the current user has an patient appointments. If no, we set the appropriate status message.
		if(appointments.size() == 0) {
			model.setStatus("You have no appointments");
		} 
		
		//Get a list of friendly choices to be displayed to the user.
		final ArrayList<String> nameChoicesForComboBox = getComboBoxChoices(appointments);
		model.setViewList(nameChoicesForComboBox);
		
	}
	
	
	/**
	 * Returns a list of appointments for the currently logged in medical staff.
	 */
	private ArrayList<Appointment> getAppointmentsForCurrentStaff() {
		//Get a list of all appointments or all users from the harddisk.
		final HashMap<String, ArrayList<Appointment>> allAppointments = DataRepository.getInstance().getAppointments();
		
		//Get all appointments for the current user. The HashMap stores the current user's username as key.
		ArrayList<Appointment> appointments = allAppointments.get(model.getUser().getUsername());
		
		if(appointments == null)
			appointments = new ArrayList<>();
		return appointments;
	}
	
	
	/**
	 * Generates a list of choices to be displayed in the combo box.
	 */
	private ArrayList<String> getComboBoxChoices(ArrayList<Appointment> appointments){
		final ArrayList<String> nameList = new ArrayList<>(appointments.size());
		for (Appointment appt: appointments) {
			nameList.add(appt.getAppointmentDate() +": " +  appt.getPatientID() + " - " + appt.getPatientName());
		}
		return nameList;
	}
	
	
	/**
	 * Set a reference to the current view.
	 */
	@Override
	public void setView(SelectionView view) {
		this.view = view;
	}
	

	/**
	 * Saves the reference to the currently selected patient.
	 */
	@Override
	public void onSelected(int index) {
		model.setSelectionIndex(index);
	}

	
	/**
	 * Returns the user back to the root menu for medical staff.
	 */
	@Override
	public void onCancel() {
		final StaffRootModel staffModel = new StaffRootModel(model.getUser());
		final StaffRootController staffRootController = new StaffRootController(staffModel);
		final StaffRootView staffView = new StaffRootView(staffRootController, staffModel);
		staffRootController.setView(staffView);
		staffView.setVisible(true);
		view.dispose();
	}

	
	/**
	 * Show the treatment entry view for the currently selected patient.
	 */
	@Override
	public void onActionPressed() {
		//If there are no appointments, we don't do anything.
		if(model.getList().isEmpty())
			return;
		final TreatmentEditorModel tModel = new TreatmentEditorModel(model.getUser(), model.getSelectedItem());
		final TreatmentEditorController tController = new TreatmentEditorController(tModel);
		final TreatmentEditorView tView = new TreatmentEditorView(tModel, tController);
		tController.setView(tView);
		tView.setVisible(true);
		view.dispose();
	}
	
	
}
