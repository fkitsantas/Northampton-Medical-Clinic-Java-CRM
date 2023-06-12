package com.northamptonmedicalclinic.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.northamptonmedicalclinic.app.models.Appointment;
import com.northamptonmedicalclinic.app.models.StaffRootModel;
import com.northamptonmedicalclinic.app.models.Treatment;
import com.northamptonmedicalclinic.app.models.TreatmentHistoryModel;
import com.northamptonmedicalclinic.app.models.TreatmentSelectionModel;
import com.northamptonmedicalclinic.app.service.DataRepository;
import com.northamptonmedicalclinic.app.views.SelectionView;
import com.northamptonmedicalclinic.app.views.StaffRootView;
import com.northamptonmedicalclinic.app.views.TreatmentHistoryView;


public class TreatmentHistorySelectionController extends BaseSelectionController {

	private TreatmentSelectionModel model;
	private SelectionView view;

	public TreatmentHistorySelectionController(TreatmentSelectionModel model) {
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
		HashSet<String> uniqueSet = new HashSet<>();		
		final ArrayList<String> nameList = new ArrayList<>(appointments.size());
		for (Appointment appt: appointments) {
			if(uniqueSet.contains(appt.getPatientID()))
				continue;
			nameList.add(appt.getPatientID() + " - " + appt.getPatientName());
			uniqueSet.add(appt.getPatientID());
		}
		return nameList;
	}
	
	/**
	 * Store a reference to selection view to send update events.
	 */
	@Override
	public void setView(SelectionView view) {
		this.view = view;
	}
	

	/**
	 * Store a  reference to currently selected item's index in our model.
	 */
	@Override
	public void onSelected(int index) {
		model.setSelectionIndex(index);
	}

	/**
	 * Return to medical staff root view.
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
	 * Show entire medical history of the currently selected patient. Do nothing if no patient has been selected.
	 */
	@Override
	public void onActionPressed() {
		//Do nothing if no patient has been selected
		if(model.getList().isEmpty())
			return;
		
		final TreatmentHistoryModel tModel = new TreatmentHistoryModel(model.getUser(),
				getTreatment(model.getSelectedItem().getPatientID())  , model.getSelectedItem().getPatientName());
		final TreatmentHistoryView tView = new TreatmentHistoryView(tModel);
		tView.setVisible(true);
		view.dispose();
	}
	
	/**
	 * Get a list of all treatments for a given patientID.
	 */
	private ArrayList<Treatment> getTreatment(String patientID){
		HashMap<String, ArrayList<Treatment>> all = DataRepository.getInstance().getTreatments();
		ArrayList<Treatment> treatment = all.get(patientID);
		if(treatment == null)
			treatment = new ArrayList<>();
		return treatment;
	}
	
	
}
