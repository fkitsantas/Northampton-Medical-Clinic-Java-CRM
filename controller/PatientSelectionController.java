package com.northamptonmedicalclinic.app.controller;

import java.util.ArrayList;

import com.northamptonmedicalclinic.app.models.AdminRootModel;
import com.northamptonmedicalclinic.app.models.Patient;
import com.northamptonmedicalclinic.app.models.PatientEditorModel;
import com.northamptonmedicalclinic.app.models.SelectionModel;
import com.northamptonmedicalclinic.app.service.DataRepository;
import com.northamptonmedicalclinic.app.views.AdminView;
import com.northamptonmedicalclinic.app.views.PatientEditorView;
import com.northamptonmedicalclinic.app.views.SelectionView;

public class PatientSelectionController extends BaseSelectionController {

	private SelectionModel<Patient> model;
	private SelectionView view;

	public PatientSelectionController(SelectionModel<Patient> model) {
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
	 * @return patient list.
	 */
	private ArrayList<Patient> getPatients(){
		final ArrayList<Patient> patients = DataRepository.getInstance().getPatientList();
		return patients;
	}
	

	/**
	 * Return a list of patient names. 
	 * @param patients
	 * @return
	 */
	private ArrayList<String> getNameList(ArrayList<Patient> patients){
		final ArrayList<String> nameList = new ArrayList<>(patients.size());
		
		for (Patient patient : patients) {
			nameList.add(patient.getId() + " - " + patient.getName());
		}
		return nameList;
	}
	
	
	/**
	 * Set the SelectionView reference.
	 */
	@Override
	public void setView(SelectionView view) {
		this.view = view;
	}
	

	/**
	 * Save the currently selected patient index in our model.
	 */
	@Override
	public void onSelected(int index) {
		model.setSelectionIndex(index);
	}

	
	/**
	 * Return to reception staff root view.
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
	 * Show the patient details editor view.
	 */
	@Override
	public void onActionPressed() {
		//Edit
		if(model.getList().isEmpty())
			return;
		PatientEditorModel sModel = new PatientEditorModel(model.getSelectedItem(), model.getSelectionIndex(), model.getUser());
		PatientEditorController mController = new PatientEditorController(sModel);
		PatientEditorView mView = new PatientEditorView(mController, sModel);
		mController.setView(mView);
		mView.setVisible(true);
		view.dispose();

	}
}
