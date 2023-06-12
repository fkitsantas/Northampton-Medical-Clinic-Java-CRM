package com.northamptonmedicalclinic.app.controller;

import java.util.ArrayList;

import com.northamptonmedicalclinic.app.models.AdminRootModel;
import com.northamptonmedicalclinic.app.models.MedicalStaff;
import com.northamptonmedicalclinic.app.models.SelectionModel;
import com.northamptonmedicalclinic.app.models.StaffEditorModel;
import com.northamptonmedicalclinic.app.service.DataRepository;
import com.northamptonmedicalclinic.app.views.AdminView;
import com.northamptonmedicalclinic.app.views.SelectionView;
import com.northamptonmedicalclinic.app.views.StaffEditorView;

public class StaffSelectionController extends BaseSelectionController {

	private SelectionModel<MedicalStaff> model;
	private SelectionView view;

	public StaffSelectionController(SelectionModel<MedicalStaff> model) {
		this.model = model;
		initialiseModel();
	}
	
	
	
	/**
	 * Initializes the model. It performs two actions:
	 * 1) A list of medical staff is stored in the model.
	 * 2) A String array is populated in the model which is used by the view to display the ComboBox choices. 
	 */
	private void initialiseModel() {
		final ArrayList<MedicalStaff> staffList = getStaffList();
		final ArrayList<String> nameList = getStaffNameList(staffList);
		
		if(staffList.isEmpty())
			model.setStatus("No staff has been added yet.");
		
		model.setList(staffList);
		model.setViewList(nameList);
		
	}
	
	/**
	 * Get the list of all medical staff.
	 */
	private ArrayList<MedicalStaff> getStaffList(){
		final ArrayList<MedicalStaff> staffList = DataRepository.getInstance().getStaffList();
		return staffList;
	}
	
	/**
	 * Get a list with just the names of all medical staff.
	 */
	private ArrayList<String> getStaffNameList(ArrayList<MedicalStaff> staffList){
		final ArrayList<String> nameList = new ArrayList<>(staffList.size());
		
		for (MedicalStaff medicalStaff : staffList) {
			nameList.add(medicalStaff.getName() + " - " + medicalStaff.getUsername());
		}
		return nameList;
	}
	
	/**
	 * Store a reference to the selection view.
	 */
	@Override
	public void setView(SelectionView view) {
		this.view = view;
	}
	

	/**
	 * Save the currently selected staff index in our model.
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
	 * Delete the currently selected medical staff and store the updated list to disk.
	 */
	@Override
	public void onActionPressed() {
		if(model.getList().isEmpty())
			return;
		
		StaffEditorModel sModel = new StaffEditorModel(model.getSelectedItem(), model.getSelectionIndex(), model.getUser());
		StaffEditorController mController = new StaffEditorController(sModel);
		StaffEditorView mView = new StaffEditorView(mController, sModel);
		mController.setView(mView);
		mView.setVisible(true);
		view.dispose();
	}
	
	
}
