package com.northamptonmedicalclinic.app.controller;

import java.util.ArrayList;

import com.northamptonmedicalclinic.app.models.AdminRootModel;
import com.northamptonmedicalclinic.app.models.Patient;
import com.northamptonmedicalclinic.app.models.SelectionModel;
import com.northamptonmedicalclinic.app.service.DataRepository;
import com.northamptonmedicalclinic.app.views.AdminView;
import com.northamptonmedicalclinic.app.views.SelectionView;


/**
 * Simple controller class to handle deletion fo a patient.
 */
public class PatientDeleteController extends BaseSelectionController {

	private SelectionModel<Patient> model;
	private SelectionView view;

	public PatientDeleteController(SelectionModel<Patient> model) {
		this.model = model;
		initialiseModel();
	}
	
	/**
	 * Populates the model with a list of patients names and the backing patient details.
	 */
	private void initialiseModel() {
		//Get a lst of patients.
		final ArrayList<Patient> patients = getPatients();
		//Get a list of names of all patients.
		final ArrayList<String> nameList = getNameList(patients);

		//Update the model
		model.setList(patients);
		model.setViewList(nameList);
	}
	
	/**
	 * Gets the list of all patients.
	 */
	private ArrayList<Patient> getPatients(){
		final ArrayList<Patient> patients = DataRepository.getInstance().getPatientList();
		return patients;
	}
	
	
	private ArrayList<String> getNameList(ArrayList<Patient> patients){
		final ArrayList<String> nameList = new ArrayList<>(patients.size());
		
		for (Patient patient : patients) {
			nameList.add(patient.getId() + " - " + patient.getName());
		}
		return nameList;
	}
	
	
	/**
	 * Set the reference to the Selection view.
	 */
	@Override
	public void setView(SelectionView view) {
		this.view = view;
	}
	

	/**
	 * Store the currently selected item index in the model.
	 */
	@Override
	public void onSelected(int index) {
		model.setSelectionIndex(index);
	}

	
	
	/**
	 * Return to the reception staff main menu.
	 */
	@Override
	public void onCancel() {
		AdminRootModel aModel = new AdminRootModel(model.getUser());
		AdminController controller = new AdminController(aModel);
		AdminView aView = new AdminView(controller, aModel);
		controller.setView(aView);
		aView.setVisible(true);
		view.dispose();
		
	}

	
	/**
	 * Delete the currently selected item.
	 */
	@Override
	public void onActionPressed() {
		//Delete the item from the model.
		model.deleteItem();
		
		//Save the updated list to disk.
		DataRepository.getInstance().savePatientList(model.getList());
		view.update();
	}
	
	
}
